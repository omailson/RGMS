package br.ufpe.cin.rgms.publicacao.apresentacao;

import java.util.List;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.base.AbstractServlet;
import br.ufpe.cin.rgms.base.RGMSException;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.publicacao.MapeamentoTipo;
import br.ufpe.cin.rgms.publicacao.modelo.ArtigoConferencia;
import br.ufpe.cin.rgms.publicacao.modelo.ArtigoPeriodico;
import br.ufpe.cin.rgms.publicacao.modelo.Nivel;
import br.ufpe.cin.rgms.publicacao.modelo.PublicacaoPosGraduacao;
import br.ufpe.cin.rgms.util.Properties;

public class AdicionarPublicacaoServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	public void logic() throws RGMSException {


			String titulo = this.formfields.get("titulo");
			String ano = this.formfields.get("ano");
			String autoresMembros = this.formfields.get("autoresmembros");
			List<Membro> membros = ListGenerator.createListaMembro(autoresMembros);
			String autoresNaoMembros = this.formfields.get("autoresnaomembros");
			List<String> naoMembros = ListGenerator.createListaNaoMembro(autoresNaoMembros);
			byte[] pdfFile = this.file;
			String tipo = this.formfields.get("tipo");
			String conferencia = this.formfields.get("conferencia");
			String paginasConf = this.formfields.get("paginasconf");
			String mes = this.formfields.get("mes");
			String jornal = this.formfields.get("jornal");
			String volume = this.formfields.get("volume");
			String numero = this.formfields.get("numero");
			String paginas = this.formfields.get("paginas");
			String universidade = this.formfields.get("universidade");
			String mesdefesa = this.formfields.get("mesdefesa");
			String nivel = this.formfields.get("nivel");

			if (Facade.getInstance().getPublicacao(titulo) != null) {
				request.setAttribute("insertpublicacaostatus", Properties.getProperty(servletContext, "titulo_ja_cadastrado"));
			} else {
				boolean insertionFlag = false;
				// Artigo em conferência
				if (tipo.equals(MapeamentoTipo.CONFERENCIA)) {
					ArtigoConferencia artigoConf = new ArtigoConferencia(membros, naoMembros, titulo, ano, pdfFile, conferencia, paginasConf, mes,
							MapeamentoTipo.CONFERENCIA);

					Facade.getInstance().inserirPublicacao(artigoConf);
					insertionFlag = true;
				}
				// Artigo periódico
				if (tipo.equals(MapeamentoTipo.PERIODICO)) {
					ArtigoPeriodico artigoPeriodico = new ArtigoPeriodico(membros, naoMembros, titulo, ano, pdfFile, jornal, volume, numero, paginas,
							MapeamentoTipo.PERIODICO);
					Facade.getInstance().inserirPublicacao(artigoPeriodico);
					insertionFlag = true;
				}
				// Pós graduação
				if (tipo.equals(MapeamentoTipo.POSGRADUACAO)) {
					if (nivel.equals(Properties.getProperty(this.getServletContext(), "mestrado"))) {
						PublicacaoPosGraduacao posGradM = new PublicacaoPosGraduacao(membros, naoMembros, titulo, ano, pdfFile, universidade, mesdefesa,
								Nivel.MESTRADO, MapeamentoTipo.POSGRADUACAO);
						Facade.getInstance().inserirPublicacao(posGradM);
						insertionFlag = true;
					}
					if (nivel.equals(Properties.getProperty(this.getServletContext(), "doutorado"))) {
						PublicacaoPosGraduacao posGradD = new PublicacaoPosGraduacao(membros, naoMembros, titulo, ano, pdfFile, universidade, mesdefesa,
								Nivel.DOUTORADO, tipo);
						Facade.getInstance().inserirPublicacao(posGradD);
						insertionFlag = true;
					}
				}

				if (insertionFlag) {
					request.setAttribute("publicacaostatus", Properties.getProperty(this.getServletContext(), "publicacao_sucesso"));
				} else {
					request.setAttribute("publicacaostatus", Properties.getProperty(this.getServletContext(), "publicacao_erro"));
				}
			}
	}

}
