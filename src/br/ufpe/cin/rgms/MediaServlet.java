package br.ufpe.cin.rgms;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public abstract class MediaServlet extends HttpServlet {

	public MediaServlet() {
		super();
	}

	/**
	 * @param response
	 * @param foto
	 * @param contentType TODO
	 * @throws IOException
	 */
	protected void sendMedia(HttpServletResponse response, 
			byte[] foto, String contentType) 
					throws IOException {
		response.setHeader("Cache-Control", "no-store");  
		response.setHeader("Pragma", "no-cache");  
		response.setDateHeader("Expires", 0);  
		response.setContentType(contentType);
	
		ServletOutputStream out = response.getOutputStream();  
		out.write(foto);  
		out.flush();  
		out.close();
	}

}