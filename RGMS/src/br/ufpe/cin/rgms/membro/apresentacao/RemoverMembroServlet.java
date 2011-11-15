package br.ufpe.cin.rgms.membro.apresentacao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.util.Properties;

public class RemoverMembroServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		Membro membro = Facade.getInstance().getMembro((String) request.getParameter("membro"));

		Facade.getInstance().removerMembro(membro);

		request.setAttribute("membrostatus", Properties.getProperty(getServletContext(), "membro_del_suc"));

		RequestDispatcher view = request.getRequestDispatcher("membrostatus.jsp");
		view.forward(request, response);
	}
}
