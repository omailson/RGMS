package br.ufpe.cin.rgms.membro.controle;

import java.util.HashMap;
import java.util.List;

import br.ufpe.cin.rgms.base.Controle;
import br.ufpe.cin.rgms.base.Persistence;
import br.ufpe.cin.rgms.base.RGMSException;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.membro.persistencia.DaoMembro;

public class ControleMembro extends Controle<Membro,DaoMembro> {

	public ControleMembro() {
		super(new DaoMembro());
	}

	public Membro getUsuario(String email) {
		Persistence.getInstance().beginTransaction();

		Membro retorno =  this.dao.getUsuario(email);
		
		DaoMembro daoMembro =  this.dao;

		Persistence.getInstance().commit();

		return retorno;
	}

	protected void validar(Membro objeto) throws RGMSException {
		if(objeto.getNome() == null || objeto.getNome().equals("") ||
				objeto.getSobrenome() == null || objeto.getSobrenome().equals("") ||
				objeto.getTipo() == null || objeto.getTipo().equals("") ||

				objeto.getDepartamento() == null || objeto.getDepartamento().equals("") ||
				objeto.getUniversidade() == null || objeto.getUniversidade().equals("") ||
				objeto.getTelefone() == null || objeto.getTelefone().equals("") ||
				objeto.getEmail() == null || objeto.getEmail().equals("") ||


				objeto.getWebsite() == null || objeto.getWebsite().equals("") ||
				objeto.getCidade() == null || objeto.getCidade().equals("") ||
				objeto.getPais() == null || objeto.getPais().equals("") ||
				objeto.getSituacao() == null || objeto.getSituacao().equals(""))
		{
			throw new RGMSException("Dados invalidos na insercao de membro.");
		}
	}

	public List<Membro> getMembros(HashMap<String, String> formfields) {
		Persistence.getInstance().beginTransaction();

		List<Membro> retorno =  this.dao.getMembros(formfields);

		Persistence.getInstance().commit();

		return retorno;
	}
}
