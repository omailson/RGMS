package br.ufpe.cin.rgms.membro.apresentacao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.membro.modelo.Membro;

public class AlterarMembroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		Membro membro = Facade.getInstance().getMembro((String) request.getParameter("membro"));

		request.setAttribute("membroalterar", membro);

		RequestDispatcher view = request.getRequestDispatcher("alterarmembro.jsp");
		view.forward(request, response);
	}
}
