package br.ufpe.cin.rgms.publicacao.persistencia;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.ufpe.cin.rgms.base.Dao;
import br.ufpe.cin.rgms.base.Persistence;
import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;

public class DaoPublicacao extends Dao<Publicacao>{

	public DaoPublicacao() {
		super(Publicacao.class);
	}

	@Override
	protected String getFirstAtributoDeOrdenacao() {
		return "titulo";
	}
	
	@Override
	protected String getSecondAtributoDeOrdenacao() {
		return "ano";
	}
	
	public boolean exists(Publicacao publication) {
		Criteria criteria = Persistence.getInstance().getSession().createCriteria(Publicacao.class);

		criteria.add(Restrictions.eq("titulo", publication.getTitulo()));
		criteria.add(Restrictions.eq("ano", publication.getAno()));

		return criteria.list().contains(publication);
	}

	public Publicacao getPublicacao(String titulo) {
		Publicacao retorno = null;
			
		Criteria criteria = Persistence.getInstance().getSession().createCriteria(Publicacao.class);
		
		criteria.add(Restrictions.eq("titulo", titulo));
		
		List<Publicacao> lista = criteria.list();
		
		if(lista.size() > 0){
			retorno = lista.get(0);
		}
		
		return retorno;
	}

	public Publicacao getPublicacao(int idPublicacao) {
		Publicacao retorno = null;
		
		Criteria criteria = Persistence.getInstance().getSession().createCriteria(Publicacao.class);

		criteria.add(Restrictions.eq("id", idPublicacao));
		
		List<Publicacao> lista = criteria.list();
		
		if(lista.size() > 0){
			retorno = lista.get(0);
		}
		
		return retorno;
	}
	
	public List<Publicacao> getPublicacoes(HashMap<String, String> formfields) {
		Criteria criteria = Persistence.getInstance().getSession().createCriteria(Publicacao.class);
		
		criteria.addOrder(Order.asc(this.getFirstAtributoDeOrdenacao()));
		criteria.addOrder(Order.asc(this.getSecondAtributoDeOrdenacao()));
		
		for(String campo : formfields.keySet()){
			String like = "%" + formfields.get(campo) + "%";
			
			criteria.add(Restrictions.ilike(campo, like));
		}
		
		return criteria.list();
	}

}
