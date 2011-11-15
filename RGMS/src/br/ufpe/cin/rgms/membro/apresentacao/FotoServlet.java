package br.ufpe.cin.rgms.membro.apresentacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.MediaServlet;

public class FotoServlet extends MediaServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		
		byte[] foto = Facade.getInstance().getFoto((String)request.getParameter("membro"));

		sendMedia(response, foto, "image/jpeg");  
	}
}  
