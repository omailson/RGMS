package br.ufpe.cin.rgms.publicacao.apresentacao;

import br.ufpe.cin.rgms.membro.modelo.Membro;

public class SFLMembro extends StringFromList<Membro> {
	
	@Override
	public String toStr(Membro t) {
		return t.nomeCompleto();
	}

}

