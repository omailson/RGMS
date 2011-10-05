package br.ufpe.cin.rgms.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.ufpe.cin.rgms.base.RGMSException;
import br.ufpe.cin.rgms.util.Properties;

public abstract class AbstractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected HashMap<String, String> formfields;
	protected byte[] file;
	protected HttpServletRequest request;
	protected ServletContext servletContext;

	@Override
	public void init() throws ServletException {
		super.init();

		this.formfields = new HashMap<String, String>();
	}

	private void processUploadedFile(FileItem item) {
		if (item.getContentType() != null) {
			this.file = item.get();
		}
	}

	private void processFormField(FileItem item) {
		// Process a regular form field
		if (item.isFormField()) {
			String name = item.getFieldName();
			String value = item.getString();

			this.formfields.put(name, value);
		}
	}

	private void extractFields(HttpServletRequest request,
			ServletFileUpload upload) throws FileUploadException {
		List<FileItem> items = upload.parseRequest(request);

		// Process the uploaded items
		Iterator<FileItem> iter = items.iterator();

		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();

			if (item.isFormField()) {
				processFormField(item);
			} 
			else {
				processUploadedFile(item);
			}
		}
	}

	public abstract void logic() throws RGMSException, FileUploadException;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		servletContext = getServletContext();

		// Parse the request
		try {

			extractFields(request, upload);

			logic();

		} catch (RGMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			request.setAttribute("publicacaostatus", Properties.getProperty(servletContext, "erro_bd"));
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

			request.setAttribute("publicacaostatus", Properties.getProperty(servletContext, "erro_upload"));
		}
		RequestDispatcher view = request.getRequestDispatcher("publicacaostatus.jsp");
		view.forward(request, response);
	}

}