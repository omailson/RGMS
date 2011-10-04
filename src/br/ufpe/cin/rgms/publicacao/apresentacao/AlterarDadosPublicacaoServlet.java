package br.ufpe.cin.rgms.publicacao.apresentacao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.base.RGMSException;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.publicacao.modelo.ArtigoConferencia;
import br.ufpe.cin.rgms.publicacao.modelo.ArtigoPeriodico;
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;
import br.ufpe.cin.rgms.publicacao.modelo.PublicacaoPosGraduacao;
import br.ufpe.cin.rgms.util.Properties;

public class AlterarDadosPublicacaoServlet extends HttpServlet {

	private HashMap<String, String> formfields;
	private byte[] pdfFile;

	@Override
	public void init() throws ServletException {
		super.init();

		this.formfields = new HashMap<String, String>();
	}

	private void processUploadedFile(FileItem item) {
		if (item.getContentType() != null) {
			this.pdfFile = item.get();
		}
	}

	private void processFormField(FileItem item) {
		// Process a regular form field
		if (item.isFormField()) {
			String name = item.getFieldName();
			String value = item.getString();

			this.formfields.put(name, value);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		ServletContext servletContext = getServletContext();

		try {
			List /* FileItem */items = upload.parseRequest(request);

			// Process the uploaded items
			Iterator iter = items.iterator();

			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();

				if (item.isFormField()) {
					processFormField(item);
				} else {
					processUploadedFile(item);
				}
			}

			String titulo = this.formfields.get("titulo");

			String ano = this.formfields.get("ano");

			byte[] pdfFile = this.pdfFile;

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

			String nivel = this.formfields.get("nivel");

			String tituloNovo = this.formfields.get("titulonovo");

			Publicacao publicacaoParaAlterar = Facade.getInstance().getPublicacao(titulo);

			/*
			 * if (!publicacaoParaAlterar.getTitulo().equals(titulo) &&
			 * Facade.getInstance().getPublicacao(titulo) != null) {
			 * request.setAttribute("publicacaostatus",
			 * "O título fornecido já foi cadastrado no sistema."); } else {
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
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

			request.setAttribute("publicacaostatus", Properties.getProperty(servletContext, "erro_bd"));
		} catch (RGMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			request.setAttribute("publicacaostatus", Properties.getProperty(servletContext, "erro_upload"));
		}

		RequestDispatcher view = request.getRequestDispatcher("publicacaostatus.jsp");
		view.forward(request, response);
	}

}
