package br.ufpe.cin.rgms;

import java.util.HashMap;
import java.util.List;

import br.ufpe.cin.rgms.base.RGMSException;
import br.ufpe.cin.rgms.membro.controle.ControleMembro;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.publicacao.controle.ControlePublicacao;
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;

public class Facade {
	
	private static Facade singleton;
	
	private ControleMembro controleMembro;
	private ControlePublicacao controlePublicacao;
	
	private Facade() {
		this.controleMembro = new ControleMembro();
		this.controlePublicacao = new ControlePublicacao();
	}
	
	public static Facade getInstance(){
		if(singleton == null){
			singleton = new Facade();
		}
		
		return singleton;
	}

	public Membro getMembro(String email) {
		return controleMembro.getUsuario(email);
	}

	public void inserirMembro(Membro membro) throws RGMSException {
		this.controleMembro.inserir(membro);
	}

	public void removerMembro(Membro membro) {
		this.controleMembro.remover(membro);
	}
	
	public List<Membro> getMembros(){
		return this.controleMembro.listar();
	}

	public void alterarMembro(Membro membro) throws RGMSException {
		this.controleMembro.alterar(membro);
	}

	public byte[] getFoto(String email) {
		Membro membro = this.controleMembro.getUsuario(email);
		
		return membro.getFoto();
	}
	
	public Publicacao getPublicacao(String titulo) {
		return this.controlePublicacao.getPublicacao(titulo);
	}

	public void inserirPublicacao(Publicacao publication) throws RGMSException {
		this.controlePublicacao.inserir(publication);
	}

	public boolean existsPublicacao(Publicacao publication) {
		return this.controlePublicacao.exists(publication);
	}

	public void removerPublicacao(Publicacao publication) {
		this.controlePublicacao.remover(publication);
	}
	
	public List<Publicacao> getPublicacoes(){
		return this.controlePublicacao.listar();
	}

	public void alterarPublicacao(Publicacao publicacao) throws RGMSException {
		this.controlePublicacao.alterar(publicacao);
	}

	public byte[] getPDF(String idPublicacao) {
		Publicacao publicacao = this.controlePublicacao.getPublicacao(Integer.parseInt(idPublicacao));
		
		return publicacao.getPdf();
	}

	public Publicacao getPublicacao(int idPublicacao) {
		return this.controlePublicacao.getPublicacao(idPublicacao);
	}

	public List<Membro> getMembros(HashMap<String, String> formfields) {
		return this.controleMembro.getMembros(formfields);
	}
	
	public List<Publicacao> getPublicacoes(HashMap<String, String> formfields) {
		return this.controlePublicacao.getPublicacoes(formfields);
	}
}
