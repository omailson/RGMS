package br.ufpe.cin.rgms.membro.apresentacao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.base.AbstractServlet;
import br.ufpe.cin.rgms.base.RGMSException;
import br.ufpe.cin.rgms.membro.modelo.Estudante;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.util.Properties;

public class AlterarDadosMembroServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	public String nome, sobrenome, universidade, departamento, vinculo, situacao, email, orientador, 
	coorientador, website, telefone, cidade, pais, emailoriginal;
	
	@Override
	public void logic() throws RGMSException, FileUploadException {
		String nome = this.formfields.get("nome");
		String sobrenome = this.formfields.get("sobrenome");
		String universidade = this.formfields.get("universidade");
		String departamento = this.formfields.get("departamento");
		String vinculo = this.formfields.get("vinculo");
		String situacao = this.formfields.get("situacao");
		String email = this.formfields.get("email");
		String orientador = this.formfields.get("orientador");
		String coorientador = this.formfields.get("coorientador");
		String website = this.formfields.get("website");
		String telefone = this.formfields.get("telefone");
		String cidade = this.formfields.get("cidade");
		String pais = this.formfields.get("pais");
		String emailoriginal = this.formfields.get("emailoriginal");
		byte[] foto = this.file;

		Membro membroParaAlterar = Facade.getInstance().getMembro(emailoriginal);

		if (!membroParaAlterar.getEmail().equals(email) && Facade.getInstance().getMembro(email) != null) {
			request.setAttribute("membrostatus", Properties.getProperty(servletContext, "e_mail_ja_cadastrado"));
		} else {
			membroParaAlterar.setEmail(email);
			membroParaAlterar.setNome(nome);
			membroParaAlterar.setSobrenome(sobrenome);
			membroParaAlterar.setTipo(vinculo);
			membroParaAlterar.setDepartamento(departamento);
			membroParaAlterar.setUniversidade(universidade);
			membroParaAlterar.setTelefone(telefone);
			membroParaAlterar.setWebsite(website);
			membroParaAlterar.setCidade(cidade);
			membroParaAlterar.setPais(pais);
			membroParaAlterar.setSituacao(situacao);

			if (foto != null) {
				membroParaAlterar.setFoto(foto);
			}

			if (membroParaAlterar instanceof Estudante) {
				Estudante estudanteParaAlterar = (Estudante) membroParaAlterar;

				estudanteParaAlterar.setOrientador(orientador);
				estudanteParaAlterar.setCoOrientador(coorientador);

				Facade.getInstance().alterarMembro(estudanteParaAlterar);
			} else {
				Facade.getInstance().alterarMembro(membroParaAlterar);
			}

			request.setAttribute("membrostatus", Properties.getProperty(servletContext, "membro_alt_suc"));
		}
		
	}
	
	@Override
	public void dispatcher(HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("membrostatus.jsp");
		view.forward(request, response);		
	}

	@Override
	public void RGMSException() {
		request.setAttribute("membrostatus", Properties.getProperty(servletContext, "erro_bd"));		
	}

	@Override
	public void FileUploadException() {
		request.setAttribute("membrostatus", Properties.getProperty(servletContext, "erro_upload"));		
	}

}
