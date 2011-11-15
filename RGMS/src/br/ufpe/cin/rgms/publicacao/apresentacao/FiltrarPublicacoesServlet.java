package br.ufpe.cin.rgms.publicacao.apresentacao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;

public class FiltrarPublicacoesServlet extends HttpServlet{
	private HashMap<String, String> formfieldsPersistente;

	@Override
	public void init() throws ServletException {
		super.init();

		this.formfieldsPersistente = new HashMap<String, String>();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		HashMap<String, String> formfields = new HashMap<String, String>();

		this.addField("titulo", request, formfields);

		this.addField("ano", request, formfields);

		this.addField("tipo", request, formfields);
		
		List<Publicacao> publicacoes = Facade.getInstance().getPublicacoes(formfields);

		request.setAttribute("publicacoes", publicacoes);
		request.setAttribute("campos", this.formfieldsPersistente);

		RequestDispatcher view = request.getRequestDispatcher("publicacoes.jsp");
		view.forward(request, response);
	}

	private void addField(String titulo, HttpServletRequest request, HashMap<String, String> formfields) {
		if(request.getParameter(titulo) != null && !request.getParameter(titulo).trim().equals("")){
			formfields.put(titulo, request.getParameter(titulo));
		}

		this.formfieldsPersistente.put(titulo, request.getParameter(titulo));

		request.removeAttribute(titulo);
	}
}
