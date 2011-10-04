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
import br.ufpe.cin.rgms.publicacao.MapeamentoTipo;
import br.ufpe.cin.rgms.publicacao.modelo.ArtigoConferencia;
import br.ufpe.cin.rgms.publicacao.modelo.ArtigoPeriodico;
import br.ufpe.cin.rgms.publicacao.modelo.Nivel;
import br.ufpe.cin.rgms.publicacao.modelo.PublicacaoPosGraduacao;
import br.ufpe.cin.rgms.util.Properties;

public class AdicionarPublicacaoServlet extends HttpServlet {
	private HashMap<String, String> formfields;
	private byte[] pdfFile;

	@Override
	public void init() throws ServletException {
		super.init();

		this.formfields = new HashMap<String, String>();
	}

	private void processUploadedFile(FileItem item) {
		this.pdfFile = item.get();
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

		// Parse the request
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

			String autoresMembros = this.formfields.get("autoresmembros");

			List<Membro> membros = ListGenerator.createListaMembro(autoresMembros);

			String autoresNaoMembros = this.formfields.get("autoresnaomembros");
			List<String> naoMembros = ListGenerator.createListaNaoMembro(autoresNaoMembros);

			byte[] pdfFile = this.pdfFile;

			String tipo = this.formfields.get("tipo");

			String conferencia = this.formfields.get("conferencia");

			String paginasConf = this.formfields.get("paginasconf");

			String mes = this.formfields.get("mes");

			String jornal = this.formfields.get("jornal");

			String volume = this.formfields.get("volume");

			String numero = this.formfields.get("numero");

			String paginas = this.formfields.get("paginas");

			String universidade = this.formfields.get("universidade");

			String mesdefesa = this.formfields.get("mesdefesa");

			String nivel = this.formfields.get("nivel");

			if (Facade.getInstance().getPublicacao(titulo) != null) {
				request.setAttribute("insertpublicacaostatus", Properties.getProperty(servletContext, "titulo_ja_cadastrado"));
			} else {
				boolean insertionFlag = false;
				// Artigo em conferência
				if (tipo.equals(MapeamentoTipo.CONFERENCIA)) {
					ArtigoConferencia artigoConf = new ArtigoConferencia(membros, naoMembros, titulo, ano, pdfFile, conferencia, paginasConf, mes,
							MapeamentoTipo.CONFERENCIA);

					Facade.getInstance().inserirPublicacao(artigoConf);
					insertionFlag = true;
				}
				// Artigo periódico
				if (tipo.equals(MapeamentoTipo.PERIODICO)) {
					ArtigoPeriodico artigoPeriodico = new ArtigoPeriodico(membros, naoMembros, titulo, ano, pdfFile, jornal, volume, numero, paginas,
							MapeamentoTipo.PERIODICO);
					Facade.getInstance().inserirPublicacao(artigoPeriodico);
					insertionFlag = true;
				}
				// Pós graduação
				if (tipo.equals(MapeamentoTipo.POSGRADUACAO)) {
					if (nivel.equals(Properties.getProperty(this.getServletContext(), "mestrado"))) {
						PublicacaoPosGraduacao posGradM = new PublicacaoPosGraduacao(membros, naoMembros, titulo, ano, pdfFile, universidade, mesdefesa,
								Nivel.MESTRADO, MapeamentoTipo.POSGRADUACAO);
						Facade.getInstance().inserirPublicacao(posGradM);
						insertionFlag = true;
					}
					if (nivel.equals(Properties.getProperty(this.getServletContext(), "doutorado"))) {
						PublicacaoPosGraduacao posGradD = new PublicacaoPosGraduacao(membros, naoMembros, titulo, ano, pdfFile, universidade, mesdefesa,
								Nivel.DOUTORADO, tipo);
						Facade.getInstance().inserirPublicacao(posGradD);
						insertionFlag = true;
					}
				}

				if (insertionFlag) {
					request.setAttribute("publicacaostatus", Properties.getProperty(this.getServletContext(), "publicacao_sucesso"));
				} else {
					request.setAttribute("publicacaostatus", Properties.getProperty(this.getServletContext(), "publicacao_erro"));
				}
			}
		} catch (RGMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			request.setAttribute("publicacaostatus", Properties.getProperty(servletContext, "erro_bd"));
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

			request.setAttribute("publicacaostatus", Properties.getProperty(servletContext, "erro_upload"));
		}
		RequestDispatcher view = request.getRequestDispatcher("publicacaostatus.jsp");
		view.forward(request, response);
	}

}
