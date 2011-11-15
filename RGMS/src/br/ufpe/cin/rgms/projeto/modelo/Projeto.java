package br.ufpe.cin.rgms.projeto.modelo;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.ufpe.cin.rgms.base.AbstractBusinessEntity;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames={"nome"})})
public class Projeto extends AbstractBusinessEntity implements Comparable<Projeto> {
	private static final long serialVersionUID = 1L;
	
	protected String nome;
	protected String descricao;
	
	public Projeto(){
		
	}
	
	public Projeto(String nome, String descricao){
		super();
		this.setNome(nome);
		this.setDescricao(descricao);
	}
	
	@Basic
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Basic
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	@Override
	public String toString() {
		return this.getNome();
	}

	@Override
	public boolean equals(Object obj) {
		return this.getNome().equals(obj);
	}

	@Override
	public int compareTo(Projeto o) {
		return this.getNome().compareTo(o.getNome());
	}

}
