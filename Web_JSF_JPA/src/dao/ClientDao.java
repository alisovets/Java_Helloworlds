package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import model.Client;
import log.Loger;

public class ClientDao extends Dao<Client> {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> findAll() {
		loger.log(Loger.Event.INFO, getClass().getName(), "findAll() method started.");
		EntityManager entityManager = null;
		try{
		entityManager = getEntityManager();
        
		CriteriaQuery criteriaQuery = entityManager.getCriteriaBuilder().createQuery();
        
        criteriaQuery.select(criteriaQuery.from(Client.class));
        return entityManager.createQuery(criteriaQuery).getResultList();
		}finally{
			 if (entityManager != null) { entityManager.close(); }
		}
	}
	
}
