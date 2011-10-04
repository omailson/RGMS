package br.ufpe.cin.rgms.publicacao.controle;

import java.util.HashMap;
import java.util.List;

import br.ufpe.cin.rgms.base.Controle;
import br.ufpe.cin.rgms.base.Persistence;
import br.ufpe.cin.rgms.base.RGMSException;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;
import br.ufpe.cin.rgms.publicacao.persistencia.DaoPublicacao;

public class ControlePublicacao extends Controle<Publicacao,DaoPublicacao> {

	public ControlePublicacao() {
		super(new DaoPublicacao());
	}

	public boolean exists(Publicacao publication) {
		Persistence.getInstance().beginTransaction();
		
		boolean retorno = this.dao.exists(publication);
		
		Persistence.getInstance().commit();
		
		return retorno;
	}

	public Publicacao getPublicacao(String titulo) {
		Persistence.getInstance().beginTransaction();
		
		Publicacao retorno =  this.dao.getPublicacao(titulo);
		
		Persistence.getInstance().commit();
		
		return retorno;
	}

	protected void validar(Publicacao objeto) throws RGMSException {
		if(objeto.getTitulo() == null || objeto.getTitulo().equals("") ||
				objeto.getAno() == null || objeto.getAno().equals("") ||
				objeto.getAutores() == null ||objeto.getAutoresNaoMembros() == null)
		{
			throw new RGMSException("Dados invalidos na insercao de publicacao.");
		}
	}

	public Publicacao getPublicacao(int idPublicacao) {
		Persistence.getInstance().beginTransaction();
		
		Publicacao retorno =  this.dao.getPublicacao(idPublicacao);
		
		Persistence.getInstance().commit();
		
		return retorno;
	}
	
	public List<Publicacao> getPublicacoes(HashMap<String, String> formfields) {
		Persistence.getInstance().beginTransaction();
		
		List<Publicacao> retorno =  this.dao.getPublicacoes(formfields);
		
		Persistence.getInstance().commit();
		
		return retorno;
	}
}
