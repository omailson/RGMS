package br.ufpe.cin.rgms.membro.modelo;

public class Nome {
	private String nome;
	private String sobrenome;

	public Nome() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String nomeCompleto(){
		return this.nome + " " + this.sobrenome;
	}
}