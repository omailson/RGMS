package br.ufpe.cin.rgms.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.base.RGMSException;
//import br.ufpe.cin.rgms.linhasdepesquisa.modelo.LinhaPesquisa;
import br.ufpe.cin.rgms.membro.modelo.Estudante;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.publicacao.modelo.ArtigoConferencia;
import br.ufpe.cin.rgms.publicacao.modelo.Nivel;
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;
import br.ufpe.cin.rgms.publicacao.modelo.PublicacaoPosGraduacao;

public class GerarBanco {
	
	public static void main(String[] args) {
		Configuration conf = new AnnotationConfiguration();
		conf.configure();		
		SchemaExport se = new SchemaExport(conf);
		se.create(true, true);

		popularBanco();
	}

	private static void popularBanco() {
		try {
			Estudante felype = new Estudante("felype.ferreira@gmail.com","Felype","Santiago","Estudante","Centro de Informática","UFPE",
					"8134395054","www.cin.ufpe.br/~fsf2","Olinda","Brasil","Ativo",null,null,"Paulo Henrique Monteiro Borba","");

			
			Facade.getInstance().inserirMembro(felype);
			List<Membro> membros = new ArrayList<Membro>();
			
			membros.add(Facade.getInstance().getMembro(felype.getEmail()));
			List<String> naoMembro = new ArrayList<String>();
			
			naoMembro.add("Michelle");
			
//			Facade.getInstance().inserirPublicacao(new ArtigoConferencia(membros,naoMembro, "Uma abordadegem de Teste", "2010", null,
//			"Conferencia de Teste", "112-113", "Maio", "Artigo em Conferência"));
//			List<String> not = new ArrayList<String>();
//			
//			Facade.getInstance().inserirPublicacao(new PublicacaoPosGraduacao(membros,not, "Linha de Produto de Software", "2012", null,
//			"UFPE", "março", Nivel.MESTRADO, "Pós-Graduação"));
			
			List<String> financiadores = new ArrayList<String>();
			financiadores.add("Financiador 1");
			financiadores.add("Financiador 2");
			financiadores.add("Financiador 3");
			
			List<String> linksRelacionados = new ArrayList<String>();
			linksRelacionados.add("Link relacionado 1");
			linksRelacionados.add("Link relacionado 2");
			linksRelacionados.add("Link relacionado 3");
			
			List<Publicacao> publicacoes = Facade.getInstance().getPublicacoes();
			
		} catch (RGMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
