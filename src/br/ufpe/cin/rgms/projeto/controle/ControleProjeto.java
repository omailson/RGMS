package br.ufpe.cin.rgms.projeto.controle;

import java.util.HashMap;
import java.util.List;

import br.ufpe.cin.rgms.base.Controle;
import br.ufpe.cin.rgms.base.Persistence;
import br.ufpe.cin.rgms.base.RGMSException;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.projeto.modelo.Projeto;
import br.ufpe.cin.rgms.projeto.persistencia.DaoProjeto;
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;

public class ControleProjeto extends Controle<Projeto, DaoProjeto> {

	public ControleProjeto() {
		super(new DaoProjeto());
	}

	@Override
	protected void validar(Projeto tipo) throws RGMSException {
		// TODO Auto-generated method stub
		
	}
	
	public Projeto getProjeto(String nome) {
		Persistence.getInstance().beginTransaction();
		
		Projeto retorno =  this.dao.getProjeto(nome);
		
		Persistence.getInstance().commit();
		
		return retorno;
	}

	public List<Projeto> getProjetos(HashMap<String, String> formfields) {
		Persistence.getInstance().beginTransaction();

		List<Projeto> retorno =  this.dao.getProjetos(formfields);

		Persistence.getInstance().commit();

		return retorno;
	}
	
	public List<String> getParticipantes(String nome) {
		Persistence.getInstance().beginTransaction();
		
		List<String> retorno =  this.dao.getParticipantes(nome);
		
		Persistence.getInstance().commit();
		
		return retorno;
	}
	
}
