package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import model.Car;
import model.Client;
import model.RentOrder;

public class RentOrderDao extends Dao<RentOrder> {

	public List<RentOrder> findByCar(int carId) {
		return null;
	}

	public List<RentOrder> findByClient(int clientId) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RentOrder> findAll() {
		EntityManager entityManager = null;
		try{
		entityManager = getEntityManager();
        
		CriteriaQuery criteriaQuery = entityManager.getCriteriaBuilder().createQuery();
        
        criteriaQuery.select(criteriaQuery.from(RentOrder.class));
        return entityManager.createQuery(criteriaQuery).getResultList();
		}finally{
			 if (entityManager != null) { entityManager.close(); }
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<RentOrder> findByClient(Client client) {
		final String jpql = "SELECT o FROM RentOrder o WHERE o.client = ?1";  
		EntityManager entityManager = null;
		try {
			entityManager = getEntityManager();
			return entityManager.createQuery(jpql)
					.setParameter(1, client)
					.getResultList();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<RentOrder> findByCar(Car car) {
		final String jpql = "SELECT o FROM RentOrder o WHERE o.car = ?1";  
		EntityManager entityManager = null;
		try {
			entityManager = getEntityManager();
			return entityManager.createQuery(jpql)
					.setParameter(1, car)
					.getResultList();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}
}
