/**
 * 
 */
package info.novatec.beantest.demo.persistence;

import info.novatec.beantest.demo.entities.Order;

import java.util.List;

/**
 * @author cb
 * 
 */
public class OrderPersistenceService extends PersistenceService<Order> {

    public OrderPersistenceService() {
        super(Order.class);
    }

    public List<Order> loadAllOrdersForCustomer(String customerId) {
        return em.createQuery("Select o from Order o where o.customerId=:customerId", Order.class)
                .setParameter("customerId", customerId).getResultList();
    }

}
