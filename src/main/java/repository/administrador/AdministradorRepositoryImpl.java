package repository.administrador;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.Administrador;
import model.Administrador_;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;



public class AdministradorRepositoryImpl implements AdministradorRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Administrador> filtrar(Administrador administrador) {

		
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<Administrador> criteria = builder.createQuery(Administrador.class);
		Root<Administrador> root = criteria.from(Administrador.class);

		@SuppressWarnings("unused")
		Predicate [] predicates = this.criarRestrisoes(administrador,builder,root);
	
		return null;
	}

	private Predicate[] criarRestrisoes(Administrador administrador, CriteriaBuilder builder,
			Root<Administrador> root) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();

		if(!StringUtils.isEmpty(administrador.getUsuario())) {
			predicates.add(builder.like(builder.lower(root.get(Administrador_.USUARIO)),
					"%" + administrador.getUsuario().toLowerCase() + "%"));
		}

		
		if(!StringUtils.isEmpty(administrador.getPermissao())) {
			predicates.add(builder.like(builder.lower(root.get(Administrador_.PERMISSAO)),
					"%" + administrador.getPermissao().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
		}
		

	@Override
	public Page<Administrador> resumir(Administrador administrador) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
