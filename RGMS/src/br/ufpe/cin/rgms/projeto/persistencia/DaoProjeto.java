package br.ufpe.cin.rgms.projeto.persistencia;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.ufpe.cin.rgms.base.Dao;
import br.ufpe.cin.rgms.base.Persistence;
import br.ufpe.cin.rgms.projeto.modelo.Projeto;

public class DaoProjeto extends Dao<Projeto>{

	public DaoProjeto() {
		super(Projeto.class);
	}

	@Override
	protected String getFirstAtributoDeOrdenacao() {
		return "nome";
	}

	@Override
	protected String getSecondAtributoDeOrdenacao() {
		return "descricao";
	}
	
	public Projeto getProjeto(String nome) {
		Projeto retorno = null;
		
		Criteria criteria = Persistence.getInstance().getSession().createCriteria(Projeto.class);

		criteria.add(Restrictions.eq("nome", nome));
		
		List<Projeto> lista = criteria.list();
		
		if(lista.size() > 0){
			retorno = lista.get(0);
		}
		
		return retorno;
	}
	
	public List<Projeto> getProjetos(HashMap<String, String> formfields) {
		Criteria criteria = Persistence.getInstance().getSession().createCriteria(Projeto.class);
		
		criteria.addOrder(Order.asc(this.getFirstAtributoDeOrdenacao()));
		criteria.addOrder(Order.asc(this.getSecondAtributoDeOrdenacao()));
		
		for(String campo : formfields.keySet()){
			String like = "%" + formfields.get(campo) + "%";
			
			criteria.add(Restrictions.ilike(campo, like));
		}
		
		return criteria.list();
	}
	
	public List<String> getParticipantes (String nome){
		String sql = "SELECT M.nome FROM membro_publicacao MP, membro M, publicacao P WHERE M.id = MP.membro_id AND P.projeto = " + this.getProjeto(nome).getId();
		List<String> list = (List<String>) Persistence.getInstance().getSession().createSQLQuery(sql).list();
		return list;
	}

}
