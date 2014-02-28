/**
 * 
 */
package info.novatec.beantest.demo.services;

import info.novatec.beantest.demo.entities.Customer;
import info.novatec.beantest.demo.persistence.CustomerPersistenceService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Carlos Barragan
 * 
 */
@Stateless
public class CustomerService {

    @EJB
    CustomerPersistenceService customerPersistenceService;

    public Customer loadCustomerByCustomerId(String customerId) {

        return customerPersistenceService.loadCustomerByCustomerId(customerId);
    }

}
