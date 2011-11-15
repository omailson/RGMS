package br.ufpe.cin.rgms.membro.modelo;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;

import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;

@Entity
public class Estudante extends Membro{
	private static final long serialVersionUID = 1L;

	private String orientador;
	
	private String coOrientador;
	
	public Estudante() {
		// TODO Auto-generated constructor stub
	}

	public Estudante(String email, String nome, String sobrenome, String tipo, 
			String departamento, String universidade, String telefone, String website,
			String cidade, String pais, String situacao, List<Publicacao> publicacoes, byte[] foto,
			String orientador, String coOrientador) {
		
		super(email, nome, sobrenome, tipo, departamento, universidade, telefone, website,
				cidade, pais, situacao, publicacoes, foto);
		
		this.setOrientador(orientador);
		this.setCoOrientador(coOrientador);
	}

	@Basic
	public String getOrientador() {
		return orientador;
	}

	public void setOrientador(String orientador) {
		this.orientador = orientador;
	}

	@Basic
	public String getCoOrientador() {
		return coOrientador;
	}

	public void setCoOrientador(String coOrientador) {
		this.coOrientador = coOrientador;
	}
}
