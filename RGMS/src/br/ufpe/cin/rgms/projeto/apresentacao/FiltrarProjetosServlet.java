package br.ufpe.cin.rgms.projeto.apresentacao;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.projeto.modelo.Projeto;

public class FiltrarProjetosServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
		private HashMap<String, String> formfieldsPersistente;

		@Override
		public void init() throws ServletException {
			super.init();
			
			this.formfieldsPersistente = new HashMap<String, String>();
		}

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			HashMap<String, String> formfields = new HashMap<String, String>();

			getFields(request, formfields);
			
			List<Projeto> projeto = Facade.getInstance().getProjetos(formfields);

			request.setAttribute("projetos", projeto);
			request.setAttribute("campos", this.formfieldsPersistente);

			RequestDispatcher view = request.getRequestDispatcher("projetos.jsp");
			view.forward(request, response);
		}

		private void getFields(HttpServletRequest request,
				HashMap<String, String> formfields) {
			this.addField("nome", request, formfields);

			this.addField("descricao", request, formfields);

		}

		private void addField(String nome, HttpServletRequest request, HashMap<String, String> formfields) {
			if(request.getParameter(nome) != null && !request.getParameter(nome).trim().equals("")){
				formfields.put(nome, request.getParameter(nome));
			}
			
			this.formfieldsPersistente.put(nome, request.getParameter(nome));

			request.removeAttribute(nome);
		}
	

	
	
}
