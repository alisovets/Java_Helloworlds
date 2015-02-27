package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import log.Loger;



//   
public abstract class Dao<T> {
	protected EntityManagerFactory emf = null;
	protected Loger loger = Loger.getInstanse();

    public Dao() {
        emf = Persistence.createEntityManagerFactory("CarRent");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

	public void add(T entity) {
		EntityManager entityManager = null;
		try {
			entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			loger.log(Loger.Event.DB_UPDATE, getClass().getName(), "new entity added");
		}catch(Exception e){
			loger.log(Loger.Event.DB_UPDATE_ERROR, getClass().getName(), "failed to add new entity");
			throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}
	

	public void remove(T entity) {
		EntityManager entityManager = null;
		try {
			entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(entity));
			entityManager.getTransaction().commit();
			loger.log(Loger.Event.DB_UPDATE, getClass().getName(), "entity removed");
		}catch(Exception e){
			loger.log(Loger.Event.DB_UPDATE_ERROR, getClass().getName(), "failed to remove entity");
			throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}	
	}

	public void update(T entity) {
		EntityManager entityManager = null;
		try {
			entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			loger.log(Loger.Event.DB_UPDATE, getClass().getName(), "entity updated");
		}catch(Exception e){
			loger.log(Loger.Event.DB_UPDATE_ERROR, getClass().getName(), "failed to update entity");
			throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}	
	}
	
    public T find(int id) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            return entityManager.find(getEntityClass(), Integer.valueOf(id));
        } finally {
            if (entityManager != null) { entityManager.close(); }
        }
    }

	abstract public List<T> findAll(); 
	
	
	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass(){
		return (Class<T>) Dao.class.getGenericSuperclass();
	}
}
