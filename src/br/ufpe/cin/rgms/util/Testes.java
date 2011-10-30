package br.ufpe.cin.rgms.util;

import br.ufpe.cin.rgms.base.ValidationException;

public class Testes {
	public String getNome() {
		return "as";
	}

	public static void main(String[] args) throws ValidationException {
		Testes t = new Testes();
		Validation<Testes> validation = new Validation<Testes>(t);
		System.out.println(validation.isValid());
		
		System.out.println(validation.executeValidations());
	}
}
