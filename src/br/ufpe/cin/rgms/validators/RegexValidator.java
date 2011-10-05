package br.ufpe.cin.rgms.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.ufpe.cin.rgms.base.IValidator;

public abstract class RegexValidator implements IValidator {
	
	@Override
	public boolean validate(String value) {
		Pattern p = Pattern.compile(this.validationPattern());
		Matcher m = p.matcher(value);
		return m.find();
	}
	
	public abstract String validationPattern();
}
