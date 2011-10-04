package br.ufpe.cin.rgms.membro.persistencia;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.ufpe.cin.rgms.base.Dao;
import br.ufpe.cin.rgms.base.Persistence;
import br.ufpe.cin.rgms.membro.modelo.Membro;

public class DaoMembro extends Dao<Membro>{

	public DaoMembro() {
		super(Membro.class);
	}

	@Override
	protected String getFirstAtributoDeOrdenacao() {
		return "tipo";
	}
	
	@Override
	protected String getSecondAtributoDeOrdenacao() {
		// TODO Auto-generated method stub
		return "nome";
	}

	public Membro getUsuario(String email) {
		Membro retorno = null;
		
		Criteria criteria = Persistence.getInstance().getSession().createCriteria(Membro.class);

		criteria.add(Restrictions.eq("email", email));
		
		List<Membro> lista = criteria.list();
		
		if(lista.size() > 0){
			retorno = lista.get(0);
		}
		
		return retorno;
	}

	public List<Membro> getMembros(HashMap<String, String> formfields) {
		Criteria criteria = Persistence.getInstance().getSession().createCriteria(Membro.class);
		
		criteria.addOrder(Order.asc(this.getFirstAtributoDeOrdenacao()));
		criteria.addOrder(Order.asc(this.getSecondAtributoDeOrdenacao()));
		
		for(String campo : formfields.keySet()){
			String like = "%" + formfields.get(campo) + "%";
			
			criteria.add(Restrictions.ilike(campo, like));
		}
		
		return criteria.list();
	}
}
