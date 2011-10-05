package br.ufpe.cin.rgms.validators;

import br.ufpe.cin.rgms.base.IValidator;

public class ObrigatorioValidator implements IValidator {
	public boolean validate(String value) {
		return (value != null && !value.equals(""));
	}

}
