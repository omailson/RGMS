package br.ufpe.cin.rgms.base;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.ufpe.cin.rgms.util.HibernateUtil;

public class Persistence {
	
	private Session session;
	
	private Transaction transaction;
	
	private static Persistence persistence;
	
	private Persistence() {
		this.session = HibernateUtil.getSession();
	}

	public static Persistence getInstance() {
		if(persistence == null){
			persistence = new Persistence();
		}
		return persistence;
	}
	
	public Session getSession() {
		return this.session;
	}
	
	public void beginTransaction() {
	//	this.session = HibernateUtil.getSession();
		if(this.session == null){
			this.session = HibernateUtil.getSession();
		}
		
		this.transaction = this.session.beginTransaction();
	}

	public void commit() {
		if(this.hasTransaction()){
			this.transaction.commit();
		}
		this.transaction = null;
	}

	public boolean hasTransaction() {
		return this.transaction != null;
	}

	public void rollback() {
		this.transaction.rollback();
		this.transaction = null;
	}

	public void close() {
		this.session.close();
	}
	
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}
