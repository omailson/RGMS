package br.ufpe.cin.rgms.util;

import java.io.IOException;
import java.io.InputStream;
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
			// Carrega as propriedades
			Properties property = new Properties();
			Class<?> objClass = this.object.getClass();

			// Pega as propriedades da classe atual e de todas as superclasses
			while (!objClass.equals(Object.class)) {
				// O nome do arquivo deve ser no formato ClasseValidation.properties
				InputStream classIs =  objClass.getResourceAsStream(String.format("%sValidation.properties", objClass.getSimpleName()));
				if (classIs != null) // Verifica se existe regra para este arquivo
					property.load(classIs);

				objClass = objClass.getSuperclass();
			}

			Enumeration<Object> enums = property.keys();
			while (enums.hasMoreElements()) {
				Object key = enums.nextElement();
				String validationClass = (String) property.get(key);
				Pattern pattern = Pattern.compile("([a-zA-Z_.]+)$");
				Matcher matcher = pattern.matcher(validationClass);
				if (matcher.find()) {
					String className = matcher.group(1);

					Class<?> c = Class.forName(className);
					IValidator validator = (IValidator) c.newInstance();
					String campo = (String) this.object.getClass()
							.getMethod(String.format("get%s", key.toString()))
							.invoke(this.object);

					if (!validator.validate(campo))
						return false;
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
