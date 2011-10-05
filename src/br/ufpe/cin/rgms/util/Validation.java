package br.ufpe.cin.rgms.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.ufpe.cin.rgms.base.IValidator;

public class Validation<T> {
	private T object;
	
	public Validation(T object) {
		this.object = object;
	}
	
	public boolean executeValidations() {
		try {
			Properties property = new Properties();
			property.load(this.object.getClass().getResourceAsStream(String.format("%sValidation.properties", this.object.getClass().getSimpleName())));
			Enumeration<Object> enums = property.keys();
			while (enums.hasMoreElements()) {
				Object key = enums.nextElement();
				String validationClass = (String) property.get(key);
				Pattern pattern = Pattern.compile("([a-zA-Z_.]+)$");
				Matcher matcher = pattern.matcher(validationClass);
				if (matcher.find()) {
					String className = matcher.group(1);
					
					Class c = Class.forName(className);
					IValidator validator = (IValidator) c.newInstance();
					String campo = (String) this.object.getClass().getMethod(String.format("get%s", key.toString())).invoke(this.object);
					
					return validator.validate(campo);
				}
			}
			
			return true;
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (IllegalArgumentException e) {
		} catch (SecurityException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		}
		
		return false;
	}
}
