package br.ufpe.cin.rgms.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.ufpe.cin.rgms.base.IValidator;

public class EmailValidator implements IValidator {

	public boolean validate(String value) {
		Pattern pattern = Pattern.compile("(.*)@(.*)\\.(.*)");
		Matcher matcher = pattern.matcher(value);
		return matcher.find();
	}

}
