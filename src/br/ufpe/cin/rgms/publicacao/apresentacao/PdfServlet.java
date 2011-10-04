package br.ufpe.cin.rgms.publicacao.apresentacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.MediaServlet;
public class PdfServlet extends MediaServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		byte[] pdf = Facade.getInstance().getPDF((String)request.getParameter("publicacao"));

		sendMedia(response, pdf, "application/pdf");  
	}
}
