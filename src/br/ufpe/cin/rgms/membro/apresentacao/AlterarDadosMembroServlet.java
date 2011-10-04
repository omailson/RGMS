package br.ufpe.cin.rgms.membro.apresentacao;

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
import br.ufpe.cin.rgms.membro.modelo.Estudante;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.util.Properties;

public class AlterarDadosMembroServlet extends HttpServlet {

	private HashMap<String, String> formfields;
	private byte[] foto;

	@Override
	public void init() throws ServletException {
		super.init();

		this.formfields = new HashMap<String, String>();
	}

	private void processUploadedFile(FileItem item) {
		if (item.getContentType() != null) {
			this.foto = item.get();
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

		ServletContext servletContext = this.getServletContext();

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

			String nome = this.formfields.get("nome");

			String sobrenome = this.formfields.get("sobrenome");

			String universidade = this.formfields.get("universidade");

			String departamento = this.formfields.get("departamento");

			String tipo = this.formfields.get("vinculo");

			String situacao = this.formfields.get("situacao");

			String email = this.formfields.get("email");

			String orientador = this.formfields.get("orientador");

			String coOrientador = this.formfields.get("coorientador");

			String website = this.formfields.get("website");

			String telefone = this.formfields.get("telefone");

			String cidade = this.formfields.get("cidade");

			String pais = this.formfields.get("pais");

			byte[] foto = this.foto;

			String emailOriginal = this.formfields.get("emailoriginal");

			Membro membroParaAlterar = Facade.getInstance().getMembro(emailOriginal);

			if (!membroParaAlterar.getEmail().equals(email) && Facade.getInstance().getMembro(email) != null) {
				request.setAttribute("membrostatus", Properties.getProperty(servletContext, "e_mail_ja_cadastrado"));
			} else {
				membroParaAlterar.setEmail(email);
				membroParaAlterar.setNome(nome);
				membroParaAlterar.setSobrenome(sobrenome);
				membroParaAlterar.setTipo(tipo);
				membroParaAlterar.setDepartamento(departamento);
				membroParaAlterar.setUniversidade(universidade);
				membroParaAlterar.setTelefone(telefone);
				membroParaAlterar.setWebsite(website);
				membroParaAlterar.setCidade(cidade);
				membroParaAlterar.setPais(pais);
				membroParaAlterar.setSituacao(situacao);

				if (foto != null) {
					membroParaAlterar.setFoto(foto);
				}

				if (membroParaAlterar instanceof Estudante) {
					Estudante estudanteParaAlterar = (Estudante) membroParaAlterar;

					estudanteParaAlterar.setOrientador(orientador);
					estudanteParaAlterar.setCoOrientador(coOrientador);

					Facade.getInstance().alterarMembro(estudanteParaAlterar);
				} else {
					Facade.getInstance().alterarMembro(membroParaAlterar);
				}

				request.setAttribute("membrostatus", Properties.getProperty(servletContext, "membro_alt_suc"));
			}

		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

			request.setAttribute("membrostatus", Properties.getProperty(servletContext, "erro_bd"));
		} catch (RGMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			request.setAttribute("membrostatus", Properties.getProperty(servletContext, "erro_upload"));
		}

		RequestDispatcher view = request.getRequestDispatcher("membrostatus.jsp");
		view.forward(request, response);
	}

	public HashMap<String, String> getFormfields() {
		return this.formfields;
	}
}
