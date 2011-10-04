package br.ufpe.cin.rgms.membro.apresentacao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.rgms.membro.controle.ControleMembro;
import br.ufpe.cin.rgms.membro.modelo.Membro;

public class DeslogarServlet extends HttpServlet {

	public void doPost( HttpServletRequest request, 
			HttpServletResponse response) 
	throws IOException, ServletException {
		request.getSession().removeAttribute("usuario");

		request.removeAttribute("loginsuccess");

		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}
}