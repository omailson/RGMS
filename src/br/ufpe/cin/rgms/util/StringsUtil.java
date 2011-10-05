package br.ufpe.cin.rgms.util;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.membro.modelo.Membro;
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;

public class StringsUtil {
	
	public static List<Membro> getMembros(String stringMembros) {
		List<String> emailsMembros = getElementsWithSplitAndTrim(stringMembros);
		List<Membro> membros = new ArrayList<Membro>();
		Membro membro = null;

		for(String email : emailsMembros){
			if(!email.equals("") && (membro = Facade.getInstance().getMembro(email)) != null){
				membros.add(membro);
			}
		}

		return membros;
	}

	public static List<String> getElementsWithSplitAndTrim(String string) {
		String[] strigsFinacionadores = string.split(",");
		List<String> retorno = new ArrayList<String>();

		for(String financador : strigsFinacionadores){
			retorno.add(financador.trim());
		}

		return retorno;
	}
	
	public static List<Publicacao> getPublicacoes(String stringIds) {
		List<String> ids = getElementsWithSplitAndTrim(stringIds);
		List<Publicacao> retorno = new ArrayList<Publicacao>();
		Publicacao publicacao = null;

		for(String id : ids){
			if(!id.trim().equals("") && (publicacao = Facade.getInstance().getPublicacao(id)) != null){
				retorno.add(publicacao);
			}
		}

		return retorno;
	}
	  private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	  private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

	  public static String toSlug(String input) {
	    String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
	    String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
	    String slug = NONLATIN.matcher(normalized).replaceAll("");
	    return slug.toLowerCase(Locale.ENGLISH);
	  }


}
