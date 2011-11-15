package br.ufpe.cin.rgms.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory factory;

	static {
		Configuration conf = new AnnotationConfiguration();
		conf.configure();
		factory = conf.buildSessionFactory();
	}

	public static Session getSession() {
		return factory.openSession();
	}
}
