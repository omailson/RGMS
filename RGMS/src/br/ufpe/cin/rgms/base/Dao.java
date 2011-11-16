package br.ufpe.cin.rgms.base;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public abstract class Dao<T> {
	protected final Class<T> classe;
	
	protected abstract String getFirstAtributoDeOrdenacao();
	
	protected abstract String getSecondAtributoDeOrdenacao();

	public Dao(Class<T> classe){
		this.classe = classe;
	}

	public void adicionar(T objeto) {
		Persistence.getInstance().getSession().save(objeto);
	}

	public void remover(T objeto){
		Persistence.getInstance().getSession().delete(objeto);
	}

	public void remover(long codigo){
		Persistence.getInstance().getSession().delete(this.procurar(codigo));
	}

	public void atualizar(T objeto){
		Persistence.getInstance().getSession().update(objeto);
	}

	public List<T> listarTudo() {
		Criteria criteria = Persistence.getInstance().getSession().createCriteria(this.classe);
		
		criteria.addOrder(Order.asc(this.getFirstAtributoDeOrdenacao()));
		criteria.addOrder(Order.asc(this.getSecondAtributoDeOrdenacao()));
		
		return criteria.list();
	}

	public T procurar(long id){
		return (T) Persistence.getInstance().getSession().load(this.classe, id);
	}

	public void rebind(T objeto){
		Persistence.getInstance().getSession().refresh(objeto);
	}

	public List<T> consulta(String atributo, String query){
		return Persistence.getInstance().getSession().createCriteria(this.classe).add(Restrictions.ilike(atributo, "%" + query + "%")).list();
	}

}