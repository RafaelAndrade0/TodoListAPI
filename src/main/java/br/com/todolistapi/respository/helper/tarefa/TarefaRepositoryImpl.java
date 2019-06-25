package br.com.todolistapi.respository.helper.tarefa;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.todolistapi.model.Tarefa;

public class TarefaRepositoryImpl implements TarefaQueries {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Page<Tarefa> filtrar(String titulo, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, Pageable pageable) {
		int firstRegister = pageable.getPageNumber() * pageable.getPageSize();
		
		Criteria criteria = this.entityManager.unwrap(Session.class).createCriteria(Tarefa.class);
		criteria.setFirstResult(firstRegister);
		criteria.setMaxResults(pageable.getPageSize());
		
		if(pageable.getSort() != null) {
			Sort.Order order = pageable.getSort().iterator().next();
			String property = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(property) : Order.desc(property));
		}
		
		this.acrescentarFiltro(criteria, titulo, dataHoraInicio, dataHoraFim);
		
		return new PageImpl<>(criteria.list(), pageable, this.total(titulo, dataHoraInicio, dataHoraFim));
	}

	private void acrescentarFiltro(Criteria criteria, String titulo, LocalDateTime dtHoraInicio, LocalDateTime dtHoraFim) {
		if(!StringUtils.isEmpty(titulo)) {
			criteria.add(Restrictions.ilike("titulo", titulo, MatchMode.ANYWHERE));
		}
		
		if(dtHoraInicio != null) {
			criteria.add(Restrictions.eq("dataHoraInicio", dtHoraInicio));
		}
		
		if(dtHoraFim != null) {
			criteria.add(Restrictions.eq("dataHoraFim", dtHoraFim));
		}
	}
	
	@Transactional(readOnly = true)
	private Long total(String titulo, LocalDateTime dtHoraInicio, LocalDateTime dtHoraFim) {
		Criteria criteria = this.entityManager.unwrap(Session.class).createCriteria(Tarefa.class);
		this.acrescentarFiltro(criteria, titulo, dtHoraInicio, dtHoraFim);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

}
