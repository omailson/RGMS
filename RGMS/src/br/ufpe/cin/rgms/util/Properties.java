package br.ufpe.cin.rgms.util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;

public class Properties {
	private static String language;
	private static String country;

	private Properties() {
	}

	private static ResourceBundle bundle;
	
	public static void setLocale(String language, String country) {
		Properties.language = language;
		Properties.country = country;
		Properties.bundle = null;
	}
	
	public static void resetLocale() {
		Properties.setLocale(null, null);
	}

	public static String getProperty(ServletContext servletContext, String key) {
		if (bundle == null) {
			String language;
			String country;
			
			if (Properties.language != null)
				language = Properties.language;
			else
				language = servletContext.getInitParameter("locale.language");
			
			if (Properties.country != null)
				country = Properties.country;
			else
				country = servletContext.getInitParameter("locale.country");

			Properties.bundle = ResourceBundle.getBundle("timepesquisa",
					new Locale(language, country));
		}
		return bundle.getString(key);
	}
}
