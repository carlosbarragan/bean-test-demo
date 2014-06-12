/**
 * 
 */
package info.novatec.beantest.demo.persistence;

import info.novatec.beantest.demo.entities.Order;

import java.util.List;

import javax.ejb.Stateless;

/**
 * @author cb
 * 
 */
@Stateless
public class OrderPersistenceService extends PersistenceService<Order> {

    public OrderPersistenceService() {
        super(Order.class);
    }

    public List<Order> loadAllOrdersForCustomer(String customerId) {
        return em.createQuery(Order.Queries.ALL_ORDERS_BY_CUSTOMER, Order.class)
                .setParameter(Order.Parameters.CUSTOMER_ID, customerId).getResultList();
    }

}
