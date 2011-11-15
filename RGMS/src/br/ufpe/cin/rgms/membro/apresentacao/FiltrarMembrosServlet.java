package br.ufpe.cin.rgms.membro.apresentacao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.membro.modelo.Membro;

public class FiltrarMembrosServlet extends HttpServlet {
	
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

		getFields(request, formfields);
		
		List<Membro> membros = Facade.getInstance().getMembros(formfields);

		request.setAttribute("membros", membros);
		request.setAttribute("campos", this.formfieldsPersistente);

		RequestDispatcher view = request.getRequestDispatcher("membros.jsp");
		view.forward(request, response);
	}

	private void getFields(HttpServletRequest request,
			HashMap<String, String> formfields) {
		this.addField("nome", request, formfields);

		this.addField("sobrenome", request, formfields);

		this.addField("universidade", request, formfields);

		this.addField("departamento", request, formfields);
		
		this.addField("tipo", request, formfields);
		
		this.addField("situacao", request, formfields);

		this.addField("email", request, formfields);
	}

	private void addField(String nome, HttpServletRequest request, HashMap<String, String> formfields) {
		if(request.getParameter(nome) != null && !request.getParameter(nome).trim().equals("")){
			formfields.put(nome, request.getParameter(nome));
		}
		
		this.formfieldsPersistente.put(nome, request.getParameter(nome));

		request.removeAttribute(nome);
	}
}
