package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import model.Car;
import model.OrderStatus;

//@Entity
public class CarDao extends Dao<Car> {
	final String SELECT_FREE_JPQL = "SELECT c FROM Car c WHERE NOT EXISTS(Select o FROM RentOrder o WHERE (o.car = c and o.status != ?1 and o.status != ?2))";

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> findAll() {
		EntityManager entityManager = null;
		try {
			entityManager = getEntityManager();

			CriteriaQuery criteriaQuery = entityManager.getCriteriaBuilder()
					.createQuery();

			criteriaQuery.select(criteriaQuery.from(Car.class));
			return entityManager.createQuery(criteriaQuery).getResultList();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

	
	@SuppressWarnings("unchecked")
	public List<Car> findFree() {
		EntityManager entityManager = null;
		try {
			entityManager = getEntityManager();
			return entityManager.createQuery(SELECT_FREE_JPQL)
					.setParameter(1, OrderStatus.RETURNED)
					.setParameter(2, OrderStatus.CLOSED)
					.getResultList();

		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}
}
