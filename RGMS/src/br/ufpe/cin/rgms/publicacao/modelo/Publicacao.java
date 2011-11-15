package br.ufpe.cin.rgms.publicacao.modelo;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CollectionOfElements;

import br.ufpe.cin.rgms.base.AbstractBusinessEntity;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.projeto.modelo.Projeto;
import br.ufpe.cin.rgms.publicacao.apresentacao.SFLMembro;
import br.ufpe.cin.rgms.publicacao.apresentacao.SFLString;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames={"titulo"})})
public abstract class Publicacao extends AbstractBusinessEntity implements Comparable<Publicacao>{ 
	private static final long serialVersionUID = 1L;
	
	public final static String CONFERENCIA = "Artigo em ConferÃªncia";
	
	public final static String PERIODICO = "Artigo em PeriÃ³dicos e Revistas";
	
	public final static String POSGRADUACAO = "PÃ³s-GraduaÃ§Ã£o";
	
	protected String tipo;
	
	protected List<Membro> autores;
	
	protected List<String> autoresNaoMembros;
	
	protected String titulo;
	protected Projeto projetoDePesquisa; 
	
	@ManyToOne
	@JoinColumn(name="projeto", referencedColumnName="ID") 
	public Projeto getProjetoDePesquisa() {
		return projetoDePesquisa;
	}
	
	public void setProjetoDePesquisa(Projeto projetoDePesquisa) {
		this.projetoDePesquisa = projetoDePesquisa;
	}


	protected String ano;
	
	protected byte[] pdf;
	
	public Publicacao() {
		// TODO Auto-generated constructor stub
	}
	public Publicacao(List<Membro> autores, List<String> autoresNaoMembros,
			String titulo, String ano, byte[] pdf, Projeto projeto, String tipo) {
		super();
		
		this.setAutores(autores);
		this.setAutoresNaoMembros(autoresNaoMembros);
		this.setTitulo(titulo);
		this.setAno(ano);
		this.setPdf(pdf);
		this.setProjetoDePesquisa(projeto);
		this.setTipo(tipo);
	}

	@ManyToMany
	@JoinTable(
			name="Membro_Publicacao",
	        joinColumns=@JoinColumn(name="publicacao_id"),
	        inverseJoinColumns=@JoinColumn(name="membro_id")
	)
	public List<Membro> getAutores() {
		return this.autores;
	}

	public void setAutores(List<Membro> autores) {
		this.autores = autores;
	}

	@Basic
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Basic
	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	@CollectionOfElements
	public List<String> getAutoresNaoMembros() {
		return autoresNaoMembros;
	}

	public void setAutoresNaoMembros(List<String> autoresNaoMembros) {
		this.autoresNaoMembros = autoresNaoMembros;
	}

	@Basic
	public byte[] getPdf() {
		return pdf;
	}

	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}
	
	@Basic
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public int compareTo(Publicacao publication) {
		return this.getTitulo().compareTo(publication.getTitulo());
	}
	
	public String referencia(){
		
		String listaAutoresMembros = new SFLMembro().listaAutores(autores);
		String listaAutoresNaoMembros = new SFLString().listaAutores(autoresNaoMembros);
		String autoresMembros = (listaAutoresMembros!=null)? listaAutoresMembros+" ": "";
		String autoresNaoMembros = (listaAutoresNaoMembros!=null)? listaAutoresNaoMembros:"";
	
		return autoresMembros+","+autoresNaoMembros+"."+this.getTitulo()+"."+this.getAno()+".";
	}
}
