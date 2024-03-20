package com.rays.common;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.rays.dto.BaseDTO;

public abstract class BaseDAOImpl<T extends BaseDTO> implements BaseDAOInt<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	public long add(T dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(T dto) {
		entityManager.merge(dto);
	}

	public void delete(T dto) {
		entityManager.remove(dto);
	}

	public T findByPK(long pk) {
		T dto = (T) entityManager.find(getDTOClass(), pk);
		return dto;
	}

	public abstract Class<T> getDTOClass();


	protected TypedQuery<T> createCriteria(T dto) {

		System.out.println("BaseDao createCriteria run");

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		// Create criteria
		CriteriaQuery<T> cq = builder.createQuery(getDTOClass());

		// Columns information
		Root<T> qRoot = cq.from(getDTOClass());

		// Column of query
		cq.select(qRoot);

		// Create where conditions
		List<Predicate> whereClause = getWhereClause(dto, builder, qRoot);

		System.out.println("After order by clause.......gopal ");
		TypedQuery<T> query = entityManager.createQuery(cq);
		System.out.println("createCriteria end here---....gopal");
		return query;

	}

	protected abstract List<Predicate> getWhereClause(T dto, CriteriaBuilder builder, Root<T> qRoot);

	public List search(T dto, int pageNo, int pageSize) {
		System.out.println("BaseDao search run");

		// System.out.println("Base DAO dto :: " + dto);
		// System.out.println(userContext);
		TypedQuery<T> query = createCriteria(dto);

		System.out.println(" PAGE ->>>>>>>>>>>>>>>>" + pageNo + " --- " + pageSize);
		if (pageSize > 0) {

			query.setFirstResult(pageNo * pageSize);
			query.setMaxResults(pageSize);
		}

		List list = query.getResultList();

		return list;
	}

}
