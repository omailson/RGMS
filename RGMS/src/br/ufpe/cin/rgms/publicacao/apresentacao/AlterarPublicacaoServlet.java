package br.ufpe.cin.rgms.publicacao.apresentacao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;

public class AlterarPublicacaoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int idPublicacao = Integer.parseInt((String) request.getParameter("publicacao"));

		Publicacao publicacao = Facade.getInstance().getPublicacao(idPublicacao);

		//Publicacao publicacao = Facade.getInstance().getPublicacao((String) request.getParameter("publicacao"));

		request.setAttribute("publicacaoalterar", publicacao);

		RequestDispatcher view = request.getRequestDispatcher("alterarpublicacao.jsp");
		view.forward(request, response);
	}
}
