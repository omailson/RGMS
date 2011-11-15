package br.ufpe.cin.rgms.publicacao.apresentacao;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.membro.modelo.Membro;

public class ListGenerator {

	public static List<Membro> createListaMembro(String autoresMembros){
		List<Membro> lista = new ArrayList<Membro>();
		String nome = "", name = "";

		String[] arrayAutoresMembros = autoresMembros.split(",");
		List<Membro> membros = Facade.getInstance().getMembros();

		for(int i = 0; i < arrayAutoresMembros.length; i++){
			nome = arrayAutoresMembros[i];

			for(int y = 0; y < membros.size(); y++){
				name = membros.get(y).nomeCompleto();
				if(nome.equals(name)){
					lista.add(membros.get(y));
				}
			}
		}

		return lista;
	}

	public static List<String> createListaNaoMembro(String autoresNaoMembros){
		List<String> lista = new ArrayList<String>();
		String[] arrayAutoresNaoMembros = autoresNaoMembros.split(",");

		for(int i = 0; i < arrayAutoresNaoMembros.length; i++){
			lista.add(arrayAutoresNaoMembros[i]);
		}

		return lista;			
	}
}
