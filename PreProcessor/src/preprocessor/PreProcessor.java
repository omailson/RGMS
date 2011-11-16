package preprocessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class PreProcessor {

	private String properties;
	private ArrayList<String> classes;
	
	public void preProcess(String filePropertyPath, String filePath) throws Exception{
		
		//Loading properties
		File fileProperty = new File(filePropertyPath);

		Properties properties = new Properties();

		properties.load(new FileInputStream(fileProperty));

		Set<Object> keySet = properties.keySet();

		VelocityContext context = new VelocityContext();

		for(Object obj : keySet){
			context.put(obj.toString(), properties.getProperty(obj.toString()));
		}

		context.put("classes", this.classes);
		
		/*  first, get and initialize an engine  */
		VelocityEngine ve = new VelocityEngine();

		File fileToPreProcess = new File(filePath);

		Properties p = new Properties(); 

		p.put("file.resource.loader.path",fileToPreProcess.getParent());  
		p.put("file.resource.loader.cache ","true");  
		p.put("file.resource.loader.modificationCheckInterval ","10000");
		File macrosVM = new File(fileToPreProcess.getParent() + File.separator + "macros.vm");
		if (macrosVM.exists())
			p.put("velocimacro.library", "macros.vm");

		ve.init(p);

		/*  next, get the Template  */
		Template t = ve.getTemplate(fileToPreProcess.getName());

		/* now render the template into a StringWriter */
		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		String[] path = filePath.split("\\.[^\\.]*$");

		FileWriter fileResult = new FileWriter(new File(path[0]));
		fileResult.write(writer.toString());
		fileResult.close();
	}
	
	public void getEntities(String path){

		String str = "classes/";
		path  = path.substring(path.indexOf(str)+str.length(), path.lastIndexOf(".class"));
		path = path.replaceAll("/", ".");
		
		ClassLoader l = URLClassLoader.getSystemClassLoader();

		try {
			
		Class<?> b = l.loadClass(path);
		for( Annotation a : b.getAnnotations()){
			if(a.annotationType().getSimpleName().equals("Entity")){
				this.classes.add(path);
				System.out.println(path + " " + a.annotationType().getSimpleName());
			}
		}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void recursive(String diretorio) throws Exception{
		
		File dir = new File(diretorio); 
		if(dir.isDirectory()){
			File list[] = dir.listFiles(); 
			
			for(File arq : list){
				
				if(arq.isDirectory()){
					recursive(arq.getAbsolutePath());
				}
				
				if(arq.isFile()){
					if(arq.getName().matches(".*\\.vsl$")){
						System.out.println(arq.getAbsolutePath());
						if(!arq.getName().equals("hibernate.cfg.xml.vsl"))
							preProcess(properties,arq.getAbsolutePath());
					}
					if(arq.getName().matches(".*\\.class$")){
						getEntities(arq.getAbsolutePath());
					}
				}
				
			}

		}else{
			preProcess(properties,dir.getAbsolutePath());
		}
	}
	
	public void setProperties(String pro){
		this.properties = pro;
	}
	
	public String getProperties(){
		return this.properties;
	}


	public static void main(String[] args) {
		try {
			args[0] = args[0].replaceAll(Pattern.quote("\\"), "/");
			args[1] = args[1].replaceAll(Pattern.quote("\\"), "/");


			PreProcessor preProcessor = new PreProcessor();
			preProcessor.setProperties(args[0]);

			preProcessor.classes = new ArrayList<String>();
			
			preProcessor.recursive(args[1]);
			
			File arq = new File(args[1]+"/src/hibernate.cfg.xml.vsl");
			preProcessor.preProcess(preProcessor.properties,arq.getAbsolutePath());
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
