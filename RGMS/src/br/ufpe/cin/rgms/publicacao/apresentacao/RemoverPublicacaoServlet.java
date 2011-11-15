package br.ufpe.cin.rgms.publicacao.apresentacao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;
import br.ufpe.cin.rgms.util.Properties;

public class RemoverPublicacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Publicacao publicacao = Facade.getInstance().getPublicacao((String) request.getParameter("publicacao"));

		Facade.getInstance().removerPublicacao(publicacao);

		request.setAttribute("publicacaostatus", Properties.getProperty(getServletContext(), "publicaco_del_suc"));

		RequestDispatcher view = request.getRequestDispatcher("publicacaostatus.jsp");
		view.forward(request, response);
	}
}
