package br.ufpe.cin.rgms.publicacao.apresentacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.publicacao.Bibtex;
import br.ufpe.cin.rgms.publicacao.MapeamentoTipo;
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;

public class BibtexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Publicacao publicacao = Facade.getInstance().getPublicacao(Integer.parseInt(request.getParameter("publicacao")));
		Bibtex bibtex = new Bibtex(publicacao);
		
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("application/bibtex");
		response.addHeader("Content-Disposition:", " attachment; filename=" + bibtex.getTituloSlug()+ ".bib");

		ServletOutputStream out = response.getOutputStream();
		bibtex.serialize(out); 
	}
}
