package br.ufpe.cin.rgms.membro.apresentacao;

import org.apache.commons.fileupload.FileUploadException;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.base.AbstractServlet;
import br.ufpe.cin.rgms.base.RGMSException;
import br.ufpe.cin.rgms.membro.modelo.Estudante;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.util.Properties;

public class AlterarDadosMembroServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void logic() throws RGMSException, FileUploadException {
		String nome = this.formfields.get("nome");
		String sobrenome = this.formfields.get("sobrenome");
		String universidade = this.formfields.get("universidade");
		String departamento = this.formfields.get("departamento");
		String tipo = this.formfields.get("vinculo");
		String situacao = this.formfields.get("situacao");
		String email = this.formfields.get("email");
		String orientador = this.formfields.get("orientador");
		String coOrientador = this.formfields.get("coorientador");
		String website = this.formfields.get("website");
		String telefone = this.formfields.get("telefone");
		String cidade = this.formfields.get("cidade");
		String pais = this.formfields.get("pais");
		byte[] foto = this.file;
		String emailOriginal = this.formfields.get("emailoriginal");

		Membro membroParaAlterar = Facade.getInstance().getMembro(emailOriginal);

		if (!membroParaAlterar.getEmail().equals(email) && Facade.getInstance().getMembro(email) != null) {
			request.setAttribute("membrostatus", Properties.getProperty(servletContext, "e_mail_ja_cadastrado"));
		} else {
			membroParaAlterar.setEmail(email);
			membroParaAlterar.setNome(nome);
			membroParaAlterar.setSobrenome(sobrenome);
			membroParaAlterar.setTipo(tipo);
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
				estudanteParaAlterar.setCoOrientador(coOrientador);

				Facade.getInstance().alterarMembro(estudanteParaAlterar);
			} else {
				Facade.getInstance().alterarMembro(membroParaAlterar);
			}

			request.setAttribute("membrostatus", Properties.getProperty(servletContext, "membro_alt_suc"));
		}
		
	}

}
