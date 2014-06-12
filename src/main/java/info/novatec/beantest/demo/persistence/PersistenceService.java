/**
 * 
 */
package info.novatec.beantest.demo.persistence;

import info.novatec.beantest.demo.entities.BaseEntity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author cb
 * 
 */
public abstract class PersistenceService<T extends BaseEntity> {

    private Class<T> entityClass;

    @PersistenceContext
    protected EntityManager em;

    protected PersistenceService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected PersistenceService() {
    }

    public T save(T entity) {
    	if(entity.getId() < 0) {
    		em.persist(entity);
    		return entity;
    	}
        return em.merge(entity);
    }

    public T find(long id) {

        return em.find(entityClass, id);
    }

    public List<T> findAll() {
        return em.createQuery("Select e from " + entityClass.getCanonicalName() + " as e",
                entityClass).getResultList();
    }

}
