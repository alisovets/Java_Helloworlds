package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import model.Bill;

public class BillDao extends Dao<Bill> {

	public List<Bill> findByRentOrder(int RentOrderId) {
		return null;
	}

	public List<Bill> findByClient(int ClientId) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bill> findAll() {
		EntityManager entityManager = null;
		try {
			entityManager = getEntityManager();

			CriteriaQuery criteriaQuery = entityManager.getCriteriaBuilder()
					.createQuery();

			criteriaQuery.select(criteriaQuery.from(Bill.class));
			return entityManager.createQuery(criteriaQuery).getResultList();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

}
