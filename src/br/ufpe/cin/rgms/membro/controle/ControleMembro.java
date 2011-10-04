package br.ufpe.cin.rgms.membro.controle;

import java.util.HashMap;
import java.util.List;

import br.ufpe.cin.rgms.base.Controle;
import br.ufpe.cin.rgms.base.Persistence;
import br.ufpe.cin.rgms.base.RGMSException;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.membro.persistencia.DaoMembro;
import br.ufpe.cin.rgms.util.StringsUtil;

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
		if(!StringsUtil.validar(objeto.getNome()) ||
				!StringsUtil.validar(objeto.getSobrenome()) ||
				!StringsUtil.validar(objeto.getTipo()) ||

				!StringsUtil.validar(objeto.getDepartamento()) ||
				!StringsUtil.validar(objeto.getUniversidade()) ||
				!StringsUtil.validar(objeto.getTelefone()) ||
				!StringsUtil.validar(objeto.getEmail()) ||


				!StringsUtil.validar(objeto.getWebsite()) ||
				!StringsUtil.validar(objeto.getCidade()) ||
				!StringsUtil.validar(objeto.getPais()) ||
				!StringsUtil.validar(objeto.getSituacao()))
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