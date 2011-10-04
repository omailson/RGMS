package br.ufpe.cin.rgms.membro.apresentacao;

import java.io.IOException;
import java.util.ArrayList;
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
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;
import br.ufpe.cin.rgms.util.Properties;

public class AdicionarMembroServlet extends HttpServlet {

	private HashMap<String, String> formfields;
	private byte[] foto;

	@Override
	public void init() throws ServletException {
		super.init();

		this.formfields = new HashMap<String, String>();
	}

	private void processUploadedFile(FileItem item) {
		this.foto = item.get();
	}

	private void processFormField(FileItem item) {
		// Process a regular form field
		if (item.isFormField()) {
			String name = item.getFieldName();
			String value = item.getString();

			this.formfields.put(name, value);
		}
	}

	public void doPost( HttpServletRequest request, 
			HttpServletResponse response) 
	throws IOException, ServletException {

		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		ServletContext servletContext = getServletContext();
		
		// Parse the request
		try {
			this.extractFields(request, upload);

			if(Facade.getInstance().getMembro(this.formfields.get("email")) != null){
				request.setAttribute("membrostatus", Properties.getProperty(servletContext, "e_mail_ja_cadastrado"));
			}
			else{
				if(this.formfields.get("vinculo").equals("Estudante")){
					Estudante estudante = new Estudante(this.formfields.get("email"), 
							this.formfields.get("nome"), this.formfields.get("sobrenome"), this.formfields.get("vinculo"), 
							this.formfields.get("departamento"), this.formfields.get("universidade"), this.formfields.get("telefone"), 
							this.formfields.get("website"), this.formfields.get("cidade"), this.formfields.get("pais"), 
							this.formfields.get("situacao"), new ArrayList<Publicacao>(), this.foto, 
							this.formfields.get("orientador"), this.formfields.get("coorientador"));

					Facade.getInstance().inserirMembro(estudante);
				}
				else{
					Membro membro = new Membro(this.formfields.get("email"), 
							this.formfields.get("nome"), this.formfields.get("sobrenome"), this.formfields.get("vinculo"), 
							this.formfields.get("departamento"), this.formfields.get("universidade"), this.formfields.get("telefone"), 
							this.formfields.get("website"), this.formfields.get("cidade"), this.formfields.get("pais"), 
							this.formfields.get("situacao"), new ArrayList<Publicacao>(), this.foto);

					Facade.getInstance().inserirMembro(membro);
				}

				request.setAttribute("membrostatus", Properties.getProperty(servletContext, "e_mail_ja_cadastrado"));
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

	private void extractFields(HttpServletRequest request,
			ServletFileUpload upload) throws FileUploadException {
		List /* FileItem */ items = upload.parseRequest(request);

		// Process the uploaded items
		Iterator iter = items.iterator();

		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();

			if (item.isFormField()) {
				processFormField(item);
			} 
			else {
				processUploadedFile(item);
			}
		}
	}

	public HashMap<String, String> getFormfields() {
		return formfields;
	}
}
