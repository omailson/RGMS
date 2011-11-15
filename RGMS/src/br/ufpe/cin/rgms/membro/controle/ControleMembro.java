package br.ufpe.cin.rgms.membro.controle;

import java.util.HashMap;
import java.util.List;

import br.ufpe.cin.rgms.base.Controle;
import br.ufpe.cin.rgms.base.Persistence;
import br.ufpe.cin.rgms.base.RGMSException;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.membro.persistencia.DaoMembro;
//import br.ufpe.cin.rgms.util.StringsUtil;
//import br.ufpe.cin.rgms.util.Validation;

public class ControleMembro extends Controle<Membro,DaoMembro> {

	public ControleMembro() {
		super(new DaoMembro());
	}

	public Membro getUsuario(String email) {
		Persistence.getInstance().beginTransaction();

		Membro retorno =  this.dao.getUsuario(email);
		
		Persistence.getInstance().commit();

		return retorno;
	}

	protected void validar(Membro objeto) throws RGMSException {
//		Validation<Membro> validation = new Validation<Membro>(objeto);
//		if (!validation.executeValidations()) {
//			throw new RGMSException("Dados invalidos na insercao de membro.");
//		}
	}

	public List<Membro> getMembros(HashMap<String, String> formfields) {
		Persistence.getInstance().beginTransaction();

		List<Membro> retorno =  this.dao.getMembros(formfields);

		Persistence.getInstance().commit();

		return retorno;
	}
}
