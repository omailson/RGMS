package br.ufpe.cin.rgms.publicacao.modelo;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;

import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.projeto.modelo.Projeto;

@Entity
public class ArtigoPeriodico extends Publicacao {
	
	private String jornal;
	
	private String volume;
	
	private String numero;
	
	private String paginas;
	
	public ArtigoPeriodico() {
		// TODO Auto-generated constructor stub
	}

	public ArtigoPeriodico(List<Membro> autores,
			List<String> autoresNaoMembros, String titulo, String ano, byte[] pdf, Projeto projeto,
			String jornal, String volume, String numero, String paginas, String tipo) {
		super(autores, autoresNaoMembros, titulo, ano, pdf, projeto, tipo);
		
		this.setJornal(jornal);
		this.setVolume(volume);
		this.setNumero(numero);
		this.setPaginas(paginas);
	}

	@Basic
	public String getJornal() {
		return jornal;
	}

	public void setJornal(String jornal) {
		this.jornal = jornal;
	}

	@Basic
	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	@Basic
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Basic
	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}
}
