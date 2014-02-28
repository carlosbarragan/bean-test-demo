/**
 * 
 */
package info.novatec.beantest.demo.persistence;

import info.novatec.beantest.demo.entities.BaseEntity;

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
        return em.merge(entity);
    }

    public T find(long id) {

        return em.find(entityClass, id);
    }

}
