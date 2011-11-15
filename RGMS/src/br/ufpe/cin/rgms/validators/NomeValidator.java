package br.ufpe.cin.rgms.validators;

public class NomeValidator extends RegexValidator {
	@Override
	public String validationPattern() {
		return "([a-zA-Z]){3,}";
	}
}
