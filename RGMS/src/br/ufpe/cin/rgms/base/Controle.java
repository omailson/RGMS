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
		this.dao.adicionar(tipo);
	}

	public List<Tipo> listar() {
		List<Tipo> retorno =  this.dao.listarTudo();
		return retorno;
	}

	public Tipo consultarUnicoResultado(long id) {
		Tipo retorno =  this.dao.procurar(id);
		return retorno;
	}
	
	public void remover(Tipo tipo){
		this.dao.remover(tipo);
	}
	
	public void remover(long id){
		this.dao.remover(id);
	}
	
	public void alterar(Tipo tipo) throws RGMSException{
		this.validar(tipo);
		this.dao.atualizar(tipo);
	}
	
	public void rebind(Tipo tipo){
		this.dao.rebind(tipo);
	}

	public List<Tipo> consulta(String atributo, String query){
		List<Tipo> retorno = this.dao.consulta(atributo,query);
		return retorno;
	}
}
