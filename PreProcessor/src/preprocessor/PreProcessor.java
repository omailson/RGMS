package preprocessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

	private String pathName;
	private String propertyFile;
	private Properties properties;
	private ArrayList<String> classes;
	private VelocityContext context;
	
	public PreProcessor(String properties, String path) throws IOException {
		this.setProperties(properties);
		this.setPath(path);
		
		this.init();
	}
	
	public void init() throws FileNotFoundException, IOException {
		//Loading properties
		File fileProperty = new File(this.propertyFile);

		this.properties = new Properties();

		this.properties.load(new FileInputStream(fileProperty));
		this.properties.put("file.resource.loader.path", this.pathName);
		this.properties.put("file.resource.loader.cache ","true");
		this.properties.put("file.resource.loader.modificationCheckInterval ","10000");
		this.properties.put("velocimacro.library", "macros.vm");
		
		Set<Object> keySet = this.properties.keySet();
		
		this.context = new VelocityContext();

		for(Object obj : keySet){
			context.put(obj.toString(), this.properties.getProperty(obj.toString()));
		}
	}
	
	public void preProcess(String filePath) throws Exception{
		/*  first, get and initialize an engine  */
		VelocityEngine ve = new VelocityEngine(this.properties);

		File fileToPreProcess = new File(filePath);

		Properties p = new Properties();

		ve.init(p);

		/*  next, get the Template  */
		Template t = ve.getTemplate(fileToPreProcess.getPath());
		/* now render the template into a StringWriter */
		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		String[] path = filePath.split("\\.[^\\.]*$");

		FileWriter fileResult = new FileWriter(new File(joinPath(this.pathName, path[0])));
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
			e.printStackTrace();
		}
	}
	
	public void recursive(String diretorio) throws Exception{
		
		File dir = new File(joinPath(this.pathName, diretorio)); 
		if(dir.isDirectory()){
			File list[] = dir.listFiles(); 
			
			for(File arq : list){
				
				if(arq.isDirectory()){
					recursive(joinPath(diretorio, arq.getName()));
				}
				
				if(arq.isFile()){
					if(arq.getName().matches(".*\\.vsl$")){
						System.out.println(joinPath(diretorio, arq.getName()));
						if(!arq.getName().equals("hibernate.cfg.xml.vsl"))
							preProcess(joinPath(diretorio, arq.getName()));
					}
					if(arq.getName().matches(".*\\.class$")){
						getEntities(arq.getAbsolutePath());
					}
				}
				
			}

		}else{
			preProcess(joinPath(diretorio, dir.getName()));
		}
	}
	
	public void setProperties(String pro){
		this.propertyFile = pro;
	}
	
	public String getProperties(){
		return this.propertyFile;
	}
	
	public void setPath(String path) throws IOException {
		File pathFile = new File(path);
		this.pathName = pathFile.getCanonicalPath();
	}
	
	public String getPath() {
		return this.pathName;
	}
	
	public void setClasses(ArrayList<String> classes) {
		this.classes = classes;
		this.context.put("classes", this.classes);
	}
	
	private static String joinPath(String path, String base) {
		return path + File.separator + base;
	}


	public static void main(String[] args) {
		try {
			args[0] = args[0].replaceAll(Pattern.quote("\\"), "/");
			args[1] = args[1].replaceAll(Pattern.quote("\\"), "/");
			
			PreProcessor preProcessor = new PreProcessor(args[0], args[1]);
			preProcessor.setClasses(new ArrayList<String>());
			
			preProcessor.recursive(".");
			
			preProcessor.preProcess("./src/hibernate.cfg.xml.vsl");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
