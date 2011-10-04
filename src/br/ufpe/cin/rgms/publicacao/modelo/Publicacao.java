package br.ufpe.cin.rgms.publicacao.modelo;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CollectionOfElements;

import br.ufpe.cin.rgms.base.AbstractBusinessEntity;
import br.ufpe.cin.rgms.membro.modelo.Membro;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames={"titulo"})})
public abstract class Publicacao extends AbstractBusinessEntity implements Comparable<Publicacao>{ 

	public final static String CONFERENCIA = "Artigo em Conferência";
	
	public final static String PERIODICO = "Artigo em Periódicos e Revistas";
	
	public final static String POSGRADUACAO = "Pós-Graduação";
	
	protected String tipo;
	
	protected List<Membro> autores;
	
	protected List<String> autoresNaoMembros;
	
	protected String titulo;
	
	protected String ano;
	
	protected byte[] pdf;
	
	public Publicacao() {
		// TODO Auto-generated constructor stub
	}
	
	public Publicacao(List<Membro> autores, List<String> autoresNaoMembros,
			String titulo, String ano, byte[] pdf, String tipo) {
		super();
		
		this.setAutores(autores);
		this.setAutoresNaoMembros(autoresNaoMembros);
		this.setTitulo(titulo);
		this.setAno(ano);
		this.setPdf(pdf);
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
	
	public String listaAutoresMembros(){
		String nomes="";
		if(this.autores.isEmpty()){
			nomes=null;
		}
		else{
			if(this.autores.size()>1){
				Iterator it = autores.iterator();
				while(it.hasNext()){
					Membro member = (Membro)it.next();
					nomes = nomes+member.getNome()+" "+member.getSobrenome()+", ";
				}
				nomes = nomes.substring(0, nomes.length()-2);
			}
			else{
				Membro m = this.autores.get(0);
				nomes = m.getNome()+" "+m.getSobrenome();	
			}						
		}
		return nomes;
	}
	
	public String listaAutoresNaoMembros(){
		String nomes="";
		if(this.autoresNaoMembros.isEmpty()){
			nomes = null;
		}
		else{
			if(this.autoresNaoMembros.size()>1){
				Iterator it = autoresNaoMembros.iterator();
				while(it.hasNext()){
					String notMember = (String)it.next();
					nomes = nomes+notMember+", ";
				}
				nomes = nomes.substring(0, nomes.length()-2);
			}
			else{
				nomes = this.autoresNaoMembros.get(0);
			}						
		}
		return nomes;
	}
	
	public String referencia(){
		String autoresMembros = (this.listaAutoresMembros()!=null)? this.listaAutoresMembros()+" ": "";
		String autoresNaoMembros = (this.listaAutoresNaoMembros()!=null)? this.listaAutoresNaoMembros():"";
	
		return autoresMembros+","+autoresNaoMembros+"."+this.getTitulo()+"."+this.getAno()+".";
	}
}
