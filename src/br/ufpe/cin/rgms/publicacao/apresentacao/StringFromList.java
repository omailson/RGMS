package br.ufpe.cin.rgms.publicacao.apresentacao;

import java.util.Iterator;
import java.util.List;

public abstract class StringFromList<T> {
	
	public String listaAutores(List<T> autores){
		String nomes = "";
		if(autores.isEmpty()){
			nomes = null;
		}else{
			
			Iterator<T> it = autores.iterator();
			while(it.hasNext()){
				T member = (T)it.next();
				nomes += toStr(member) + ", ";
			}
			nomes = nomes.substring(0, nomes.length()-2);

		}
		return nomes;
	}
	
	public abstract String toStr(T t);
}
