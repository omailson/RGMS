package br.ufpe.cin.rgms.publicacao.apresentacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.rgms.Facade;

public class PdfServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		byte[] pdf = Facade.getInstance().getPDF((String)request.getParameter("publicacao"));

		response.setHeader("Cache-Control", "no-store");  
		response.setHeader("Pragma", "no-cache");  
		response.setDateHeader("Expires", 0);  
		response.setContentType("application/pdf");

		ServletOutputStream out = response.getOutputStream();
		out.write(pdf );  
		out.flush();  
		out.close();  
	}
}
