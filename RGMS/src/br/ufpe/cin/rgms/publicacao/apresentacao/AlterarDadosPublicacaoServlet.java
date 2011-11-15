package br.ufpe.cin.rgms.publicacao.apresentacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.base.AbstractServlet;
import br.ufpe.cin.rgms.base.RGMSException;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.publicacao.modelo.ArtigoConferencia;
import br.ufpe.cin.rgms.publicacao.modelo.ArtigoPeriodico;
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;
import br.ufpe.cin.rgms.publicacao.modelo.PublicacaoPosGraduacao;
import br.ufpe.cin.rgms.util.Properties;

public class AlterarDadosPublicacaoServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void logic() throws RGMSException, FileUploadException {

		byte[] pdfFile = this.file;
		String titulo = this.formfields.get("titulo");
		String ano = this.formfields.get("ano");
		String autoresMembros = this.formfields.get("autoresmembros");
		List<Membro> membros = ListGenerator.createListaMembro(autoresMembros);
		String autoresNaoMembros = this.formfields.get("autoresnaomembros");
		List<String> naoMembros = ListGenerator.createListaNaoMembro(autoresNaoMembros);
		String conferencia = this.formfields.get("conferencia");
		String paginasConf = this.formfields.get("paginasconf");
		String mes = this.formfields.get("mes");
		String jornal = this.formfields.get("jornal");
		String volume = this.formfields.get("volume");
		String numero = this.formfields.get("numero");
		String paginas = this.formfields.get("paginas");
		String universidade = this.formfields.get("universidade");
		String mesDefesa = this.formfields.get("mesdefesa");
		//String nivel = this.formfields.get("nivel");
		//String tituloNovo = this.formfields.get("titulonovo");

		Publicacao publicacaoParaAlterar = Facade.getInstance().getPublicacao(titulo);

		/*
		 * if (!publicacaoParaAlterar.getTitulo().equals(titulo) &&
		 * Facade.getInstance().getPublicacao(titulo) != null) {
		 * request.setAttribute("publicacaostatus",
		 * "O t�tulo fornecido j� foi cadastrado no sistema."); } else {
		 */

		publicacaoParaAlterar.setAno(ano);
		publicacaoParaAlterar.setAutores(membros);
		publicacaoParaAlterar.setAutoresNaoMembros(naoMembros);
		publicacaoParaAlterar.setTitulo(titulo);

		if (pdfFile != null) {
			publicacaoParaAlterar.setPdf(pdfFile);
		}

		if (publicacaoParaAlterar instanceof ArtigoConferencia) {
			ArtigoConferencia artigoParaAlterar = (ArtigoConferencia) publicacaoParaAlterar;

			artigoParaAlterar.setConferencia(conferencia);
			artigoParaAlterar.setPaginas(paginasConf);
			artigoParaAlterar.setMes(mes);

			Facade.getInstance().alterarPublicacao(artigoParaAlterar);
		}
		if (publicacaoParaAlterar instanceof ArtigoPeriodico) {
			ArtigoPeriodico artigoPeriodicoParaAlterar = (ArtigoPeriodico) publicacaoParaAlterar;

			artigoPeriodicoParaAlterar.setJornal(jornal);
			artigoPeriodicoParaAlterar.setVolume(volume);
			artigoPeriodicoParaAlterar.setNumero(numero);
			artigoPeriodicoParaAlterar.setPaginas(paginas);

			Facade.getInstance().alterarPublicacao(artigoPeriodicoParaAlterar);
		}
		if (publicacaoParaAlterar instanceof PublicacaoPosGraduacao) {
			PublicacaoPosGraduacao publicacaoPosParaAlterar = (PublicacaoPosGraduacao) publicacaoParaAlterar;

			publicacaoPosParaAlterar.setUniversidade(universidade);
			publicacaoPosParaAlterar.setMes(mesDefesa);
			/*
			 * if(nivel.equals("Mestrado")){
			 * publicacaoPosParaAlterar.setNivel(Nivel.MESTRADO); } else{
			 * publicacaoPosParaAlterar.setNivel(Nivel.DOUTORADO); }
			 */

			Facade.getInstance().alterarPublicacao(publicacaoPosParaAlterar);
		}

		request.setAttribute("publicacaostatus", Properties.getProperty(servletContext, "publicaco_alt_suc"));
		
	}
	
	@Override
	public void dispatcher(HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("publicacaostatus.jsp");
		view.forward(request, response);		
	}

	@Override
	public void RGMSException() {
		request.setAttribute("publicacaostatus", Properties.getProperty(servletContext, "erro_bd"));		
	}

	@Override
	public void FileUploadException() {
		request.setAttribute("publicacaostatus", Properties.getProperty(servletContext, "erro_upload"));		
	}


}
