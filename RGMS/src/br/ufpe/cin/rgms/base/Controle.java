package br.ufpe.cin.rgms.base;

import java.util.List;

public abstract class Controle<Tipo,DaoTipo extends Dao<Tipo>> {
	
	protected DaoTipo dao;
	
	protected abstract void validar(Tipo tipo) throws RGMSException;

	public Controle(DaoTipo dao) {
		this.dao = dao;
	}
	
	public void inserir(Tipo tipo) throws RGMSException {
		validar(tipo);
		
		Persistence.getInstance().beginTransaction();
		
		this.dao.adicionar(tipo);
		
		Persistence.getInstance().commit();
	}

	public List<Tipo> listar() {
		Persistence.getInstance().beginTransaction();
		
		List<Tipo> retorno =  this.dao.listarTudo();
		
		Persistence.getInstance().commit();
		
		return retorno;
	}

	public Tipo consultarUnicoResultado(long id) {
		Persistence.getInstance().beginTransaction();
		
		Tipo retorno =  this.dao.procurar(id);
		
		Persistence.getInstance().commit();
		
		return retorno;
	}
	
	public void remover(Tipo tipo){
		Persistence.getInstance().beginTransaction();
		
		this.dao.remover(tipo);
		
		Persistence.getInstance().commit();
	}
	
	public void remover(long id){
		Persistence.getInstance().beginTransaction();
		
		this.dao.remover(id);
		
		Persistence.getInstance().commit();
	}
	
	public void alterar(Tipo tipo) throws RGMSException{
		this.validar(tipo);
		
		Persistence.getInstance().beginTransaction();
		
		this.dao.atualizar(tipo);
		
		Persistence.getInstance().commit();
	}
	
	public void rebind(Tipo tipo){
		Persistence.getInstance().beginTransaction();
		
		this.dao.rebind(tipo);
		
		Persistence.getInstance().commit();
	}

	public List<Tipo> consulta(String atributo, String query){
		Persistence.getInstance().beginTransaction();
		
		List<Tipo> retorno = this.dao.consulta(atributo,query);
		
		Persistence.getInstance().commit();
		
		return retorno;
	}
}
