package br.ufpe.cin.rgms.membro.modelo;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.ufpe.cin.rgms.base.AbstractBusinessEntity;
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class Membro extends AbstractBusinessEntity implements
		Comparable<Membro> {
	/**
	 * Nome Tipo (estudante, pesquisador...) se for estudante, informar
	 * orientador e co-orientador (caso se aplique) Departamento/Universidade
	 * e-mail telefone de contato website Cidade / Pa’s Foto Ativo / Former
	 * member
	 */
	public final static String ESTUDANTE = "Estudante";

	public final static String PESQUISADOR = "Pesquisador";

	public final static String OUTROS = "Outros";

	protected String nome;

	protected String sobrenome;

	protected String tipo;

	protected String departamento;

	protected String universidade;

	protected String telefone;

	protected String email;

	protected String website;

	protected String cidade;

	protected String pais;

	protected String situacao;

	protected List<Publicacao> publicacoes;

	protected byte[] foto;

	public Membro() {
		// TODO Auto-generated constructor stub
	}

	public Membro(String email, String nome, String sobrenome, String tipo,
			String departamento, String universidade, String telefone,
			String website, String cidade, String pais, String situacao,
			List<Publicacao> publicacoes, byte[] foto) {

		super();

		this.setEmail(email);
		this.setNome(nome);
		this.setSobrenome(sobrenome);
		this.setTipo(tipo);
		this.setDepartamento(departamento);
		this.setUniversidade(universidade);
		this.setTelefone(telefone);
		this.setWebsite(website);
		this.setCidade(cidade);
		this.setPais(pais);
		this.setSituacao(situacao);
		this.setPublicacoes(publicacoes);
		this.setFoto(foto);
	}

	@Basic
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Basic
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	@Basic
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Basic
	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Basic
	public String getUniversidade() {
		return universidade;
	}

	public void setUniversidade(String universidade) {
		this.universidade = universidade;
	}

	@Basic
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Basic
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Basic
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Basic
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Basic
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@ManyToMany
	@JoinTable(name = "Membro_Publicacao", joinColumns = @JoinColumn(name = "membro_id"), inverseJoinColumns = @JoinColumn(name = "publicacao_id"))
	public List<Publicacao> getPublicacoes() {
		return publicacoes;
	}

	public void setPublicacoes(List<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
	}

	@Basic
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;

		if (obj instanceof Membro) {
			Membro membro = (Membro) obj;

			if (this.email != null && this.email.equals(membro.email)) {
				equals = true;
			}
		}

		return equals;
	}

	@Override
	public int compareTo(Membro membro) {
		return this.getNome().compareTo(membro.getNome());
	}

	@Override
	public String toString() {
		return this.getEmail();
	}
}
