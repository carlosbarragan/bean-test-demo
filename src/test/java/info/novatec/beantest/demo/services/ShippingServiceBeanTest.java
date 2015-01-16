/**
 * 
 */
package info.novatec.beantest.demo.services;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import info.novatec.beantest.api.BaseBeanTest;
import info.novatec.beantest.demo.entities.Customer;
import info.novatec.beantest.demo.entities.CustomerStatus;
import info.novatec.beantest.demo.entities.Order;
import info.novatec.beantest.demo.entities.ShippingAddress;
import info.novatec.beantest.demo.mocks.PersistenceServiceUtils;
import info.novatec.beantest.demo.persistence.CustomerPersistenceService;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Carlos Barragan
 * 
 */
public class ShippingServiceBeanTest extends BaseBeanTest {

    private final ShippingAddress shippingAddressInGermany = new ShippingAddress(Locale.GERMANY,
            "Eine Strasse", "12345");

    private Order orderWithShippingAddressInGermany = new Order();

    @Before
    public void setUp() {
        PersistenceServiceUtils persistenceUtils = getBean(PersistenceServiceUtils.class);
        persistenceUtils.executeInTransaction(em -> em.createQuery("Delete from Customer")
                .executeUpdate());

        orderWithShippingAddressInGermany = new Order();
        orderWithShippingAddressInGermany.setCustomerId("12345");
        orderWithShippingAddressInGermany.setShippingAddress(shippingAddressInGermany);

        Customer customer = new Customer("Harald", "12345", CustomerStatus.VIP);
        CustomerPersistenceService customerPs = getBean(CustomerPersistenceService.class);
        customerPs.save(customer);
    }

    @Test
    public void shouldCalculateShippingForCustomerInGermanyAndVipNonPlusUltraStatus() {
        ShippingService cut = getBean(ShippingService.class);
        assertThat(cut.calculateShipping(orderWithShippingAddressInGermany), is(0.0));
    }

}
