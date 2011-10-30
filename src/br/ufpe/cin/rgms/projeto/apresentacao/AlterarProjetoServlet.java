package br.ufpe.cin.rgms.projeto.apresentacao;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.projeto.modelo.Projeto;

public class AlterarProjetoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			Projeto projeto = Facade.getInstance().getProjeto((String) request.getParameter("projeto"));

			request.setAttribute("projetoalterar", projeto);

			RequestDispatcher view = request.getRequestDispatcher("alterarprojeto.jsp");
			view.forward(request, response);
		}

	
}
