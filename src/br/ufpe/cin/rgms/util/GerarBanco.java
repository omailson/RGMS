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
import br.ufpe.cin.rgms.publicacao.MapeamentoTipo;
import br.ufpe.cin.rgms.publicacao.modelo.ArtigoConferencia;
import br.ufpe.cin.rgms.publicacao.modelo.ArtigoPeriodico;
import br.ufpe.cin.rgms.publicacao.modelo.Nivel;
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;
import br.ufpe.cin.rgms.publicacao.modelo.PublicacaoPosGraduacao;

public class GerarBanco {
	
	public static void main(String[] args) {
		Configuration conf = new AnnotationConfiguration();
		conf.configure();		
		SchemaExport se = new SchemaExport(conf);
		se.create(true, true);

		popularBanco2();
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
	
	public static void popularBanco2() {
		try {
			Estudante e1 = new Estudante(
					"mdlm@cin.ufpe.br", 
					"Mailson", 
					"Menezes", 
					"Estudante", 
					"CIn", 
					"UFPE", 
					"8135555555", 
					"http://www.cin.ufpe.br/~mdlm/", 
					"Recife", 
					"Brasil", 
					"Ativo", 
					null, 
					null, 
					"Fulano de Tal", 
					"Sicrano");
			Facade.getInstance().inserirMembro(e1);
			
			Estudante e2 = new Estudante(
					"lqlm@cin.ufpe.br", 
					"Lucas", 
					"Queiroz", 
					"Estudante", 
					"CIn", 
					"UFPE", 
					"8135555555", 
					"http://www.cin.ufpe.br/~lqlm/", 
					"Recife", 
					"Brasil", 
					"Ativo", 
					null, 
					null, 
					"Fulano de Tal", 
					"Sicrano");
			Facade.getInstance().inserirMembro(e2);
			
			List<Membro> autores1 = new ArrayList<Membro>();
			autores1.add(Facade.getInstance().getMembro(e1.getEmail()));
			autores1.add(Facade.getInstance().getMembro(e2.getEmail()));
			List<String> autoresNaoMembros1 = new ArrayList<String>();
			autoresNaoMembros1.add("Michelle");
			ArtigoConferencia a1 = new ArtigoConferencia(
					autores1, 
					autoresNaoMembros1, 
					"Sobre artigos de conferência", 
					"2011", 
					null,			// PDF 
					"ICIP", 
					"2", 
					"10", 
					MapeamentoTipo.CONFERENCIA);
			Facade.getInstance().inserirPublicacao(a1);
			
			// TODO Cadastrar grupo de pesquisa
			List<Membro> membros1 = new ArrayList<Membro>();
			membros1.add(Facade.getInstance().getMembro(e1.getEmail()));
			membros1.add(Facade.getInstance().getMembro(e2.getEmail()));
			
			List<Publicacao> artigos1 = new ArrayList<Publicacao>();
			artigos1.add(Facade.getInstance().getPublicacao(a1.getTitulo()));
			
			// Cadastrar o grupo de verdade
			// membros1 são os membros do grupo
			// artigos1 são os artigos publicados pelo grupo
			// Grupo g1 = new Grupo(membros1, artigos1, "Grupo AEIOU", "O grupo estuda os conceitos de grupos de pesquisa");
			// Facade.getInstance().inserirGrupo(g1);
			
			// ---
			
			Estudante e3 = new Estudante(
					"embat@cin.ufpe.br", 
					"Eduardo", 
					"Embat", 
					"Estudante", 
					"CIn", 
					"UFPE", 
					"8135555555", 
					"http://www.cin.ufpe.br/~embat/", 
					"Recife", 
					"Brasil", 
					"Ativo", 
					null, 
					null, 
					"Paulo Gonçalves", 
					"");
			Facade.getInstance().inserirMembro(e3);
			
			List<Membro> autores3 = new ArrayList<Membro>();
			autores3.add(Facade.getInstance().getMembro(e3.getEmail()));
			List<String> autoresNaoMembros3 = new ArrayList<String>();
			autoresNaoMembros3.add("Amanda");
			ArtigoPeriodico a3 = new ArtigoPeriodico(
					autores3, 
					autoresNaoMembros3, 
					"Estudo do comportamento de usuários nas mailing-lists", 
					"2011", 
					null, 
					"Whatever Journal", 
					"3", 
					"2", 
					"81-115", 
					MapeamentoTipo.PERIODICO);
			Facade.getInstance().inserirPublicacao(a3);
			
			Estudante e4 = new Estudante(
					"jma@cin.ufpe.br", 
					"Jonathas", 
					"Alves", 
					"Estudante", 
					"CIn", 
					"UFPE", 
					"8135555555", 
					"http://www.cin.ufpe.br/~jma/", 
					"Recife", 
					"Brasil", 
					"Ativo", 
					null, 
					null, 
					"Silvio Melo", 
					"");
			Facade.getInstance().inserirMembro(e4);
			
			Estudante e5 = new Estudante(
					"evc2@cin.ufpe.br", 
					"Ellís", 
					"Queiroz", 
					"Estudante", 
					"CIn", 
					"UFPE", 
					"8135555555", 
					"http://www.cin.ufpe.br/~evc2/", 
					"Recife", 
					"Brasil", 
					"Ativo", 
					null, 
					null, 
					"Fernando Castor", 
					"Paulo Borba");
			Facade.getInstance().inserirMembro(e5);
			
			List<Membro> autores4 = new ArrayList<Membro>();
			autores4.add(Facade.getInstance().getMembro(e4.getEmail()));
			autores4.add(Facade.getInstance().getMembro(e5.getEmail()));
			List<String> autoresNaoMembros4 = new ArrayList<String>();
			PublicacaoPosGraduacao a4 = new PublicacaoPosGraduacao(
					autores4, 
					autoresNaoMembros4, 
					"Desenvolvimento de softwares nas nuvens", 
					"2010", 
					null, 
					"UFPE", 
					"10", 
					Nivel.MESTRADO, 
					MapeamentoTipo.POSGRADUACAO);
			Facade.getInstance().inserirPublicacao(a4);
			
			Estudante e6 = new Estudante(
					"ass5@cin.ufpe.br", 
					"Airton", 
					"Sampaio", 
					"Estudante", 
					"CIn", 
					"UFPE", 
					"8135555555", 
					"http://www.cin.ufpe.br/~ass5/", 
					"Recife", 
					"Brasil", 
					"Ativo", 
					null, 
					null, 
					"Fulano de Tal", 
					"Sicrano");
			Facade.getInstance().inserirMembro(e6);
			
			// TODO Cadastro do grupo de pesquisa
			List<Membro> membros3 = new ArrayList<Membro>();
			membros3.add(Facade.getInstance().getMembro(e3.getEmail()));
			membros3.add(Facade.getInstance().getMembro(e4.getEmail()));
			membros3.add(Facade.getInstance().getMembro(e5.getEmail()));
			membros3.add(Facade.getInstance().getMembro(e6.getEmail()));
			
			List<Publicacao> artigos3 = new ArrayList<Publicacao>();
			artigos3.add(Facade.getInstance().getPublicacao(a3.getTitulo()));
			artigos3.add(Facade.getInstance().getPublicacao(a4.getTitulo()));
			
			// TODO Cadastrar o grupo de verdade
			// Grupo g3 = new Grupo(membros3, artigos3, "CIn Labs", "Grupo de estudo do comportamento das redes do CIn");
			// Facade.getInstance().inserirGrupo(g3);
			
		} catch (RGMSException e) {
			e.printStackTrace();
		}
	}
	

}
