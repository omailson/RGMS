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
	private static final long serialVersionUID = 1L;

	protected Nome nome = new Nome();

	protected Contato contato = new Contato();

	protected Endereco endereco = new Endereco();

	protected Vinculo vinculo = new Vinculo();

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
		return nome.getNome();
	}

	public void setNome(String nome) {
		this.nome.setNome(nome);
	}

	@Basic
	public String getSobrenome() {
		return nome.getSobrenome();
	}

	public void setSobrenome(String sobrenome) {
		this.nome.setSobrenome(sobrenome);
	}
	
	public String nomeCompleto(){
		return this.nome.nomeCompleto();
	}

	@Basic
	public String getTipo() {
		return vinculo.getTipo();
	}

	public void setTipo(String tipo) {
		this.vinculo.setTipo(tipo);
	}

	@Basic
	public String getDepartamento() {
		return vinculo.getDepartamento();
	}

	public void setDepartamento(String departamento) {
		this.vinculo.setDepartamento(departamento);
	}

	@Basic
	public String getUniversidade() {
		return vinculo.getUniversidade();
	}

	public void setUniversidade(String universidade) {
		this.vinculo.setUniversidade(universidade);
	}

	@Basic
	public String getTelefone() {
		return contato.getTelefone();
	}

	public void setTelefone(String telefone) {
		this.contato.setTelefone(telefone);
	}

	@Basic
	public String getEmail() {
		return contato.getEmail();
	}

	public void setEmail(String email) {
		this.contato.setEmail(email);
	}

	@Basic
	public String getWebsite() {
		return contato.getWebsite();
	}

	public void setWebsite(String website) {
		this.contato.setWebsite(website);
	}

	@Basic
	public String getCidade() {
		return endereco.getCidade();
	}

	public void setCidade(String cidade) {
		this.endereco.setCidade(cidade);
	}

	@Basic
	public String getPais() {
		return endereco.getPais();
	}

	public void setPais(String pais) {
		this.endereco.setPais(pais);
	}

	@Basic
	public String getSituacao() {
		return vinculo.getSituacao();
	}

	public void setSituacao(String situacao) {
		this.vinculo.setSituacao(situacao);
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

			if (this.contato.getEmail() != null && this.contato.getEmail().equals(membro.contato.getEmail())) {
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
