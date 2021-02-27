package repository.telefone;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.TelefoneUtil;
import model.TelefoneUtil_;
import org.springframework.cglib.core.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.TelefoneUtil;
import model.TelefoneUtil_;
import org.springframework.cglib.core.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;


public class TelefoneUtilRepositoryImpl implements TelefoneUtilRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<TelefoneUtil> pesquisar(TelefoneUtil telefoneUtil) {

		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<TelefoneUtil> criteria = builder.createQuery(TelefoneUtil.class);
		Root<TelefoneUtil> root = criteria.from(TelefoneUtil.class);
		
		Predicate [] predicates = this.criaRestrisoes(telefoneUtil,builder,root);
		
		return null;
	}

	@Override
	public Page<TelefoneUtil> filtrar(TelefoneUtil telefoneUtil) {
		
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<TelefoneUtil> criteria = builder.createQuery(TelefoneUtil.class);
		Root<TelefoneUtil> root = criteria.from(TelefoneUtil.class);
		
		Predicate [] predicates = this.criaRestrisoes(telefoneUtil,builder,root);
		
		return null;
	}

	@Override
	public Page<TelefoneUtil> resumir(TelefoneUtil telefoneUtil) {
		
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<TelefoneUtil> criteria = builder.createQuery(TelefoneUtil.class);
		Root<TelefoneUtil> root = criteria.from(TelefoneUtil.class);
		
		Predicate [] predicates = this.criaRestrisoes(telefoneUtil,builder,root);
		
		return null;
	}
	
	private Predicate[] criaRestrisoes(TelefoneUtil telefoneUtil, CriteriaBuilder builder, Root<TelefoneUtil> root) {

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(telefoneUtil.getNome())) {
			predicates.add((Predicate) builder.like(builder.lower(root.get(TelefoneUtil_.NOME)),
					"%" + telefoneUtil.getNome().toLowerCase() + "%"));
		}
		
		if(!StringUtils.isEmpty(telefoneUtil.getTelefone1())) {
			predicates.add((Predicate) builder.like(builder.lower(root.get(TelefoneUtil_.TELEFONE1)),
					"%" + telefoneUtil.getTelefone1().toLowerCase() + "%"));
		}
		
		if(!StringUtils.isEmpty(telefoneUtil.getTelefone2())) {
			predicates.add((Predicate) builder.like(builder.lower(root.get(TelefoneUtil_.TELEFONE2)),
					"%" + telefoneUtil.getTelefone2().toLowerCase() + "%"));
		}
			
		if(!StringUtils.isEmpty(telefoneUtil.getTelefone3())) {
			predicates.add((Predicate) builder.like(builder.lower(root.get(TelefoneUtil_.TELEFONE3)),
					"%" + telefoneUtil.getTelefone3().toLowerCase() + "%"));
		}
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	
	
}
