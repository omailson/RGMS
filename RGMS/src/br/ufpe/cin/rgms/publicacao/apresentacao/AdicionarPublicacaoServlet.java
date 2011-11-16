package br.ufpe.cin.rgms.publicacao.apresentacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.base.AbstractServlet;
import br.ufpe.cin.rgms.base.RGMSException;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.projeto.modelo.Projeto;
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
		String autoresmembros = this.formfields.get("autoresmembros");
		String autoresnaomembros = this.formfields.get("autoresnaomembros");
		String tipo = this.formfields.get("tipo");
		String conferencia = this.formfields.get("conferencia");
		String paginasconf = this.formfields.get("paginasconf");
		String mes = this.formfields.get("mes");
		String jornal = this.formfields.get("jornal");
		String volume = this.formfields.get("volume");
		String numero = this.formfields.get("numero");
		String paginas = this.formfields.get("paginas");
		String universidade = this.formfields.get("universidade");
		String mesdefesa = this.formfields.get("mesdefesa");
		String nivel = this.formfields.get("nivel");

			List<Membro> membros = ListGenerator.createListaMembro(autoresmembros);
			List<String> naomembros = ListGenerator.createListaNaoMembro(autoresnaomembros);
			byte[] pdfFile = this.file;
			String projeto = this.formfields.get("projeto");
			Projeto projetoobj = Facade.getInstance().getProjeto(projeto);

			if (Facade.getInstance().getPublicacao(titulo) != null) {
				request.setAttribute("insertpublicacaostatus", Properties.getProperty(servletContext, "titulo_ja_cadastrado"));
			} else {
				boolean insertionFlag = false;
				// Artigo em conferÃªncia
				if (tipo.equals(MapeamentoTipo.CONFERENCIA)) {
					ArtigoConferencia artigoConf = new ArtigoConferencia(membros, naomembros, titulo, ano, pdfFile, projetoobj, conferencia, paginasconf, mes,
							MapeamentoTipo.CONFERENCIA);

					Facade.getInstance().inserirPublicacao(artigoConf);
					insertionFlag = true;
				}
				// Artigo periÃ³dico
				if (tipo.equals(MapeamentoTipo.PERIODICO)) {
					ArtigoPeriodico artigoPeriodico = new ArtigoPeriodico(membros, naomembros, titulo, ano, pdfFile, projetoobj, jornal, volume, numero, paginas,
							MapeamentoTipo.PERIODICO);
					Facade.getInstance().inserirPublicacao(artigoPeriodico);
					insertionFlag = true;
				}
				// PÃ³s graduaÃ§Ã£o
				if (tipo.equals(MapeamentoTipo.POSGRADUACAO)) {
					if (nivel.equals(Properties.getProperty(this.getServletContext(), "mestrado"))) {
						PublicacaoPosGraduacao posGradM = new PublicacaoPosGraduacao(membros, naomembros, titulo, ano, pdfFile, projetoobj, universidade, mesdefesa,
								Nivel.MESTRADO, MapeamentoTipo.POSGRADUACAO);
						Facade.getInstance().inserirPublicacao(posGradM);
						insertionFlag = true;
					}
					if (nivel.equals(Properties.getProperty(this.getServletContext(), "doutorado"))) {
						PublicacaoPosGraduacao posGradD = new PublicacaoPosGraduacao(membros, naomembros, titulo, ano, pdfFile, projetoobj, universidade, mesdefesa,
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

	@Override
	public void dispatcher(HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("publicacaostatus.jsp");
		view.forward(request, response);		
	}

	@Override
	public void RGMSException() {
		request.setAttribute("publicacaostatus", Properties.getProperty(servletContext, "erro_bd"));		
	}

	@Override
	public void FileUploadException() {
		request.setAttribute("publicacaostatus", Properties.getProperty(servletContext, "erro_upload"));		
	}

}
