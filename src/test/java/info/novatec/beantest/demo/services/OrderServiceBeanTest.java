/**
 * 
 */
package info.novatec.beantest.demo.services;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import info.novatec.beantest.api.BaseBeanTest;
import info.novatec.beantest.demo.entities.Customer;
import info.novatec.beantest.demo.entities.CustomerStatus;
import info.novatec.beantest.demo.entities.Item;
import info.novatec.beantest.demo.entities.Order;
import info.novatec.beantest.demo.entities.OrderItem;
import info.novatec.beantest.demo.entities.ShippingAddress;
import info.novatec.beantest.demo.persistence.CustomerPersistenceService;

import java.util.Locale;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author cb
 * 
 */
public class OrderServiceBeanTest extends BaseBeanTest {

    private static final String VIP_CUSTOMER_ID = "12345";

    private static final String VIP_NON_PLUS_ULTRA_CUSTOMER_ID = "2345";

    private final ShippingAddress shippingAddressInGermany = new ShippingAddress(Locale.GERMANY,
            "Eine Strasse", VIP_CUSTOMER_ID);

    private Order orderWithShippingAddressInGermany;

    @Before
    public void setUp() {
        orderWithShippingAddressInGermany = new Order();

        orderWithShippingAddressInGermany.setShippingAddress(shippingAddressInGermany);

        Customer customer = new Customer("Harald", VIP_CUSTOMER_ID, CustomerStatus.VIP);
        CustomerPersistenceService customerPs = getBean(CustomerPersistenceService.class);
        customerPs.save(customer);

        customer = new Customer("Martin", VIP_NON_PLUS_ULTRA_CUSTOMER_ID,
                CustomerStatus.VIP_NON_PLUS_ULTRA);
        customerPs.save(customer);
    }

    @Test
    public void shouldCalculateOderCostForVIPCustomerInGermany() {
        OrderService orderService = getBean(OrderService.class);
        orderWithShippingAddressInGermany.setCustomerId(VIP_CUSTOMER_ID);
        Stream.of(Item.create("item1", 10), Item.create("item2", 5.0))
                .map((Item item) -> new OrderItem(item).withQuantity(2))
                .forEach(orderWithShippingAddressInGermany::addOrderitem);

        double orderCost = orderService.calculateOrderPrice(orderWithShippingAddressInGermany);
        assertThat(orderCost, is(30.0));
    }

    @Test
    public void shouldCalculateOderCostForVIPNonPlusUltraCustomerInGermany() {
        OrderService orderService = getBean(OrderService.class);
        orderWithShippingAddressInGermany.setCustomerId(VIP_NON_PLUS_ULTRA_CUSTOMER_ID);

        Stream.of(Item.create("item1", 10), Item.create("item2", 5.0))
                .map((Item item) -> new OrderItem(item).withQuantity(2))
                .forEach(orderWithShippingAddressInGermany::addOrderitem);

        double orderCost = orderService.calculateOrderPrice(orderWithShippingAddressInGermany);
        assertThat(orderCost, is(28.5));
    }
}
