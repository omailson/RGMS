package br.ufpe.cin.rgms.membro.apresentacao;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.base.AbstractServlet;
import br.ufpe.cin.rgms.base.RGMSException;
import br.ufpe.cin.rgms.membro.modelo.Estudante;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;
import br.ufpe.cin.rgms.util.Properties;

public class AdicionarMembroServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void logic() throws RGMSException, FileUploadException {
		if(Facade.getInstance().getMembro(this.formfields.get("email")) != null){
			request.setAttribute("membrostatus", Properties.getProperty(servletContext, "e_mail_ja_cadastrado"));
		}
		else{
			if(this.formfields.get("vinculo").equals("Estudante")){
				Estudante estudante = new Estudante(this.formfields.get("email"), 
						this.formfields.get("nome"), this.formfields.get("sobrenome"), this.formfields.get("vinculo"), 
						this.formfields.get("departamento"), this.formfields.get("universidade"), this.formfields.get("telefone"), 
						this.formfields.get("website"), this.formfields.get("cidade"), this.formfields.get("pais"), 
						this.formfields.get("situacao"), new ArrayList<Publicacao>(), this.file, 
						this.formfields.get("orientador"), this.formfields.get("coorientador"));

				Facade.getInstance().inserirMembro(estudante);
			}
			else{
				Membro membro = new Membro(this.formfields.get("email"), 
						this.formfields.get("nome"), this.formfields.get("sobrenome"), this.formfields.get("vinculo"), 
						this.formfields.get("departamento"), this.formfields.get("universidade"), this.formfields.get("telefone"), 
						this.formfields.get("website"), this.formfields.get("cidade"), this.formfields.get("pais"), 
						this.formfields.get("situacao"), new ArrayList<Publicacao>(), this.file);

				Facade.getInstance().inserirMembro(membro);
			}

			request.setAttribute("membrostatus", Properties.getProperty(servletContext, "e_mail_ja_cadastrado"));
		}
		
	}
	
	@Override
	public void dispatcher(HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("membrostatus.jsp");
		view.forward(request, response);		
	}

	@Override
	public void RGMSException() {
		request.setAttribute("membrostatus", Properties.getProperty(servletContext, "erro_bd"));		
	}

	@Override
	public void FileUploadException() {
		request.setAttribute("membrostatus", Properties.getProperty(servletContext, "erro_upload"));		
	}

}
