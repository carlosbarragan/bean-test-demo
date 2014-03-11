/**
 * 
 */
package info.novatec.beantest.demo.persistence;

import info.novatec.beantest.demo.entities.Customer;

import javax.ejb.Stateless;

/**
 * @author Carlos Barragan
 * 
 */
@Stateless
public class CustomerPersistenceService extends PersistenceService<Customer> {

    public CustomerPersistenceService() {
        super(Customer.class);
    }

    public Customer loadCustomerByCustomerId(String customerId) {
        return em
                .createQuery("Select c from Customer as c where c.customerId=:customerId",
                        Customer.class).setParameter("customerId", customerId).getSingleResult();
    }

}
