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
import br.ufpe.cin.rgms.projeto.modelo.Projeto;
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

		popularBanco();
	}

	public static void popularBanco() {
		try {			
			Projeto p1 = new Projeto("Grupo AEIOU", "O grupo estuda os conceitos de grupos de pesquisa");
			Facade.getInstance().inserirProjeto(p1);
			
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
					Facade.getInstance().getProjeto(p1.getNome()),
					"ICIP", 
					"2", 
					"10", 
					MapeamentoTipo.CONFERENCIA);
			Facade.getInstance().inserirPublicacao(a1);
			
			// ---
			
			Projeto p3 = new Projeto("CIn Labs", "Grupo de estudo do comportamento das redes do CIn");
			Facade.getInstance().inserirProjeto(p3);
			
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
					Facade.getInstance().getProjeto(p3.getNome()),
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
					Facade.getInstance().getProjeto(p3.getNome()),
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
						
		} catch (RGMSException e) {
			e.printStackTrace();
		}
	}
	

}
