package preprocessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class PreProcessor {

	private String properties;
	
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

		//context.put("vari", true);
		
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
						preProcess(properties,arq.getAbsolutePath());
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

			preProcessor.recursive(args[1]);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
