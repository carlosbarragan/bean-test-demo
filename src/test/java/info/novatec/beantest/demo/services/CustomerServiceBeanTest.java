/**
 * 
 */
package info.novatec.beantest.demo.services;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import info.novatec.beantest.api.BaseBeanTest;
import info.novatec.beantest.demo.entities.Customer;
import info.novatec.beantest.demo.entities.CustomerStatus;
import info.novatec.beantest.demo.persistence.CustomerPersistenceService;

import javax.persistence.PersistenceException;

import org.junit.Test;

/**
 * @author cb
 * 
 */
public class CustomerServiceBeanTest extends BaseBeanTest {

    @Test
    public void shouldFindCustomer() {

        CustomerPersistenceService customerPs = getBean(CustomerPersistenceService.class);

        assertThat(customerPs.findAll(), is(empty()));

        Customer customer = new Customer("John", "123456", CustomerStatus.NORMAL);

        customer = customerPs.save(customer);
        assertThat(customer.getId(), not(0L));

        Customer customerFromDb = customerPs.find(customer.getId());

        assertThat(customerFromDb, is(notNullValue()));
        assertThat(customer.getId(), is(customerFromDb.getId()));
        assertThat(customer.getName(), is(customerFromDb.getName()));
        assertThat(customer.getCustomerId(), is(customerFromDb.getCustomerId()));

    }

    @Test(expected = PersistenceException.class)
    public void shouldFailBecauseOfUniqueCustomerId() {
        CustomerPersistenceService customerPs = getBean(CustomerPersistenceService.class);
        Customer customer = new Customer("John", "123456", CustomerStatus.NORMAL);

        customerPs.save(customer);

        customer = new Customer("Max", "123456", CustomerStatus.NORMAL);
        customerPs.save(customer);
        fail("Should have failed because of uniqueness violation");

    }

}
