package info.novatec.beantest.demo.services;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import info.novatec.beantest.demo.backend.BackEndOrderSystem;
import info.novatec.beantest.demo.entities.Item;
import info.novatec.beantest.demo.entities.Order;
import info.novatec.beantest.demo.entities.OrderItem;
import info.novatec.beantest.demo.entities.ShippingAddress;
import info.novatec.beantest.demo.persistence.OrderPersistenceService;

import java.util.Locale;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OrderServiceUnitTest {

	@Mock
	BackEndOrderSystem backEndOrderSystem;

	@Mock
	OrderPersistenceService orderPersistenceService; 
	
	@InjectMocks
	OrderService cut = new OrderService();
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldPlaceOrder() {
		ShippingAddress shippingAddressInGermany = new ShippingAddress(Locale.GERMANY,
	            "Eine Strasse", "80765");
		Order order = new Order();
		order.setShippingAddress(shippingAddressInGermany);
		order.setCustomerId("1234");
		order.setTotalPrice(123.45);
		
		Stream.of(Item.create("item1", 10), Item.create("item2", 5.0))
        .map((Item item) -> new OrderItem(item).withQuantity(2))
        .forEach(orderItem -> order.addOrderitem(orderItem));
		
		cut.placeOrder(order);
		ArgumentCaptor<Order> orderArgumentCaptor=ArgumentCaptor.forClass(Order.class);
		
		verify(backEndOrderSystem).placeOrder(orderArgumentCaptor.capture());
		
		assertThat(order.getCustomerId(), is(orderArgumentCaptor.getValue().getCustomerId()));
		
	}

}
