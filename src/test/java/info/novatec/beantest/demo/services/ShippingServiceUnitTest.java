/**
 * 
 */
package info.novatec.beantest.demo.services;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import info.novatec.beantest.demo.entities.Customer;
import info.novatec.beantest.demo.entities.CustomerStatus;
import info.novatec.beantest.demo.entities.Item;
import info.novatec.beantest.demo.entities.Order;
import info.novatec.beantest.demo.entities.OrderItem;
import info.novatec.beantest.demo.entities.ShippingAddress;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author cb
 * 
 */
public class ShippingServiceUnitTest {

    @Mock
    private CustomerService customerService;

    private final ShippingAddress shippingAddressInGermany = new ShippingAddress(Locale.GERMANY,
            "Eine Strasse", "12345");
    private Order orderWithShippingAddressInGermany = new Order();

    @InjectMocks
    private final ShippingService cut = new ShippingService();

    @Before
    public void initMocks() {
        orderWithShippingAddressInGermany = new Order();
        orderWithShippingAddressInGermany.setCustomerId("12345");
        orderWithShippingAddressInGermany.setShippingAddress(shippingAddressInGermany);

        MockitoAnnotations.initMocks(this);
        reset(customerService);
        when(customerService.loadCustomerByCustomerId(eq("12345"))).thenReturn(new Customer("name", "123", CustomerStatus.VIP));

    }

    @Test
    public void shouldCalculateShippingForCustomerInGermanyAndVipNonPlusUltraStatus() {

        Assert.assertThat(cut.calculateShipping(orderWithShippingAddressInGermany), is(0.0));
    }

    @Test
    public void shouldCalculateShippingForCustomerInGermanyAndVipStatusWithMoreThanFivieItems() {

        orderWithShippingAddressInGermany.addOrderitem(new OrderItem(Item.create("item", 1.0))
                .withQuantity(6));

        assertThat(cut.calculateShipping(orderWithShippingAddressInGermany), is(3.0));
    }

    @Test
    public void shouldCalculateShippingForCustomerInGermanyAndVipStatusWithLessThanFivieItems() {

        orderWithShippingAddressInGermany.addOrderitem(new OrderItem(Item.create("item", 1.0))
                .withQuantity(4));

        assertThat(cut.calculateShipping(orderWithShippingAddressInGermany), is(0.0));
    }

    @Test
    public void shouldCalculateShippingForCustomerInGermanyAndNormalStatus() {
        when(customerService.loadCustomerByCustomerId(Mockito.eq("12345"))).thenReturn(
                new Customer("name", "123", CustomerStatus.NORMAL));

        orderWithShippingAddressInGermany.addOrderitem(new OrderItem(Item.create("item", 1.0))
                .withQuantity(4));

        assertThat(cut.calculateShipping(orderWithShippingAddressInGermany), is(4.0));
    }
}
