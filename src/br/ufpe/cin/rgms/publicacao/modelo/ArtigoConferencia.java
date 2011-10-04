package br.ufpe.cin.rgms.publicacao.modelo;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;

import br.ufpe.cin.rgms.membro.modelo.Membro;

@Entity
public class ArtigoConferencia extends Publicacao {
	
	private String conferencia;
	
	private String paginasConf;
	
	private String mes;
	
	public ArtigoConferencia() {
		// TODO Auto-generated constructor stub
	}

	public ArtigoConferencia(List<Membro> autores,
			List<String> autoresNaoMembros, String titulo, String ano, byte[] pdf,
			String conferencia, String paginas, String mes, String tipo) {
		super(autores, autoresNaoMembros, titulo, ano, pdf, tipo);
		
		this.setConferencia(conferencia);
		this.setPaginas(paginas);
		this.setMes(mes);
	}

	@Basic
	public String getConferencia() {
		return conferencia;
	}

	public void setConferencia(String conferencia) {
		this.conferencia = conferencia;
	}

	@Basic
	public String getPaginas() {
		return paginasConf;
	}

	public void setPaginas(String paginas) {
		this.paginasConf = paginas;
	}

	@Basic
	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}
}
