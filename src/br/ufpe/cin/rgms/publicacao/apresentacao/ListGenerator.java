package br.ufpe.cin.rgms.publicacao.apresentacao;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.rgms.Facade;
import br.ufpe.cin.rgms.membro.modelo.Membro;

public class ListGenerator {

	public static List<Membro> createListaMembro(String autoresMembros){
		List<Membro> lista = new ArrayList<Membro>();
		String nome="";
		for(int x=0; x<autoresMembros.length(); x++){
			if(autoresMembros.charAt(x)!=','){
				nome = nome+autoresMembros.charAt(x);		
			}
			if((nome.length()==autoresMembros.length())||(autoresMembros.charAt(x)==',')){
				if(autoresMembros.charAt(x)==','){
					nome = nome.substring(0, x-1);	
				}
				List<Membro> membros = Facade.getInstance().getMembros();
				int y=0;
				String name="";
				while(y<membros.size()){

					name = membros.get(y).getNome()+" "+membros.get(y).getSobrenome();
					if(nome.equals(name)){
						lista.add(membros.get(y));
					}			
					y++;
				}
				nome="";
			}

		}
		return lista;


	}

	public static List<String> createListaNaoMembro(String autoresNaoMembros){
		List<String> lista = new ArrayList<String>();
		String nome="";
		for(int x=0; x<autoresNaoMembros.length()-1;x++){
			if(autoresNaoMembros.charAt(x)!=','){
				nome = nome+autoresNaoMembros.charAt(x);				
			}
			if((nome.length()==autoresNaoMembros.length())||(autoresNaoMembros.charAt(x)==',')){
				if(autoresNaoMembros.charAt(x)==','){
					nome = nome.substring(0, x-1);	
				}
				lista.add(nome);
				nome = "";
			}	
		}
		return lista;			
	}
}
