package br.ufpe.cin.rgms.util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;

public class Properties {

	private Properties() {
	}

	private static ResourceBundle bundle;

	public static String getProperty(ServletContext servletContext, String key) {
		if (bundle == null) {
			Properties.bundle = ResourceBundle.getBundle("timepesquisa",
					new Locale(servletContext.getInitParameter("locale.language"), servletContext.getInitParameter("locale.country")));
		}
		return bundle.getString(key);
	}
}
