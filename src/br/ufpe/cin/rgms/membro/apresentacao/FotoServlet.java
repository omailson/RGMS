package br.ufpe.cin.rgms.membro.apresentacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.rgms.Facade;

public class FotoServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		byte[] foto = Facade.getInstance().getFoto((String)request.getParameter("membro"));

		response.setHeader("Cache-Control", "no-store");  
		response.setHeader("Pragma", "no-cache");  
		response.setDateHeader("Expires", 0);  
		response.setContentType("image/jpeg");

		ServletOutputStream out = response.getOutputStream();  
		out.write(foto);  
		out.flush();  
		out.close();  
	}
}  
