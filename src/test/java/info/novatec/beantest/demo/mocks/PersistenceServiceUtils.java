/**
 * 
 */
package info.novatec.beantest.demo.mocks;

import java.util.function.Consumer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author cb
 * 
 */
@Stateless
public class PersistenceServiceUtils {

    @PersistenceContext
    EntityManager em;

    public void executeInTransaction(Consumer<EntityManager> executionBlock) {
        executionBlock.accept(em);
    }

}
