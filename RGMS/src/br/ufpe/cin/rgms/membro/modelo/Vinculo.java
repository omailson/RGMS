package br.ufpe.cin.rgms.membro.modelo;

public class Vinculo {
	private String tipo;
	private String departamento;
	private String universidade;
	private String situacao;
	public final static String OUTROS = "Outros";
	public final static String PESQUISADOR = "Pesquisador";
	public final static String ESTUDANTE = "Estudante";


	public Vinculo() {
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getUniversidade() {
		return universidade;
	}

	public void setUniversidade(String universidade) {
		this.universidade = universidade;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}