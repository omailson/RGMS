package br.ufpe.cin.rgms.projeto.apresentacao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.base.AbstractServlet;
import br.ufpe.cin.rgms.base.RGMSException;
import br.ufpe.cin.rgms.projeto.modelo.Projeto;
import br.ufpe.cin.rgms.util.Properties;

public class AlterarDadosProjetoServlet extends AbstractServlet{
	private static final long serialVersionUID = 1L;

		@Override
		public void logic() throws RGMSException, FileUploadException {
			String nome = this.formfields.get("nome");
			String descricao = this.formfields.get("descricao");
			String nomeOriginal = this.formfields.get("nomeoriginal");

			Projeto projetoParaAlterar = Facade.getInstance().getProjeto(nomeOriginal);

			if (!projetoParaAlterar.getNome().equals(nome) && Facade.getInstance().getProjeto(nome) != null) {
				request.setAttribute("projetostatus", Properties.getProperty(servletContext, "nome_ja_cadastrado"));
			} else {
				projetoParaAlterar.setNome(nome);
				projetoParaAlterar.setDescricao(descricao);

				request.setAttribute("projetostatus", Properties.getProperty(servletContext, "projeto_alt_suc"));
			}
			
		}
		
		@Override
		public void dispatcher(HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher view = request.getRequestDispatcher("projetostatus.jsp");
			view.forward(request, response);		
		}

		@Override
		public void RGMSException() {
			request.setAttribute("projetostatus", Properties.getProperty(servletContext, "erro_bd"));		
		}

		@Override
		public void FileUploadException() {
			request.setAttribute("projetostatus", Properties.getProperty(servletContext, "erro_upload"));		
		}



	
}
