package br.ufpe.cin.rgms.publicacao;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.publicacao.modelo.ArtigoConferencia;
import br.ufpe.cin.rgms.publicacao.modelo.ArtigoPeriodico;
import br.ufpe.cin.rgms.publicacao.modelo.Nivel;
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;
import br.ufpe.cin.rgms.publicacao.modelo.PublicacaoPosGraduacao;
import br.ufpe.cin.rgms.util.StringsUtil;

public class Bibtex {
	Map<String, String> propriedades;
	String tipoBibtex = "";
	String tituloSlug = "";

	public String getTituloSlug() {
		return tituloSlug;
	}

	public Bibtex(Publicacao publicacao) {
		String tipo = publicacao.getTipo();

		propriedades = new HashMap<String, String>();
		String titulo = publicacao.getTitulo();
		tituloSlug = StringsUtil.toSlug(titulo);
		propriedades.put("title", titulo);
		propriedades.put("year", publicacao.getAno());

		// Constrói string de autores no formato bibtex blabla, blab and
		// blabla2, blabla
		List<Membro> autores = publicacao.getAutores();
		StringBuilder membrosBuilder = new StringBuilder();
		Iterator<Membro> membrosIterator = autores.iterator();
		while (membrosIterator.hasNext()) {
			Membro membro = membrosIterator.next();
			membrosBuilder.append(membro.getSobrenome()).append(",").append(membro.getNome());
			if (membrosIterator.hasNext()) {
				membrosBuilder.append("and");
			}
		}

		// Constrói string de autores não-membros e caso contenha algum
		// conteúdo, é concatenada com a string de autores membros
		StringBuilder naoMembrosBuilder = new StringBuilder();
		List<String> autoresNaoMembros = publicacao.getAutoresNaoMembros();
		Iterator<String> iterator = autoresNaoMembros.iterator();
		while (iterator.hasNext()) {
			String naoMembro = (String) iterator.next();
			naoMembrosBuilder.append(naoMembro);
			if (membrosIterator.hasNext()) {
				membrosBuilder.append("and");
			}
		}

		if (naoMembrosBuilder.length() > 0) {
			membrosBuilder.append("and");
			membrosBuilder.append(naoMembrosBuilder.toString());
		}

		propriedades.put("author", membrosBuilder.toString());

		if (tipo.equals(MapeamentoTipo.CONFERENCIA)) {
			tipoBibtex = "@inproceedings";
			ArtigoConferencia publicacaoConferecencia = (ArtigoConferencia) publicacao;
			propriedades.put("booktitle", publicacaoConferecencia.getConferencia());
			propriedades.put("pages", publicacaoConferecencia.getPaginas());
			propriedades.put("month", publicacaoConferecencia.getMes());
		} else if (tipo.equals(MapeamentoTipo.PERIODICO)) {
			tipoBibtex = "@article";
			ArtigoPeriodico publicacaoPeriodico = (ArtigoPeriodico) publicacao;
			propriedades.put("journal", publicacaoPeriodico.getJornal());
			propriedades.put("volume", publicacaoPeriodico.getVolume());
			propriedades.put("pages", publicacaoPeriodico.getPaginas());
			propriedades.put("number", publicacaoPeriodico.getNumero());
		} else if (tipo.equals(MapeamentoTipo.POSGRADUACAO)) {
			PublicacaoPosGraduacao publicacaoPos = (PublicacaoPosGraduacao) publicacao;
			propriedades.put("month", publicacaoPos.getMes());
			if (publicacaoPos.getNivel().equals(Nivel.MESTRADO)) {
				tipoBibtex = "@mastersthesis";
			} else {
				tipoBibtex = "@phdthesis";
			}
		}
	}
	
	public void serialize(OutputStream out) throws IOException {
		out.write(tipoBibtex.getBytes());
		out.write("{".getBytes());
		out.write(tituloSlug.getBytes());
		out.write(",\n".getBytes());
		Set<Entry<String, String>> entrySet = propriedades.entrySet();
		for (Entry<String, String> entry : entrySet) {
			out.write(entry.getKey().getBytes());
			out.write(" = ".getBytes());
			out.write(entry.getValue().getBytes());
			out.write(",\n".getBytes());
		}
		out.write("}".getBytes());
		
	}
}
