package br.ufpe.cin.rgms.membro.modelo;

public class EmailValidation {
	public boolean validate(String email) {
		if (email.contains("@")) {
			return true;
		} else {
			return false;
		}
	}
}
