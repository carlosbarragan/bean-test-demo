/**
 * 
 */
package info.novatec.beantest.demo.services;

import static info.novatec.beantest.demo.entities.CustomerStatus.VIP_NON_PLUS_ULTRA;
import info.novatec.beantest.demo.backend.BackEndOrderSystem;
import info.novatec.beantest.demo.entities.Customer;
import info.novatec.beantest.demo.entities.Order;
import info.novatec.beantest.demo.entities.OrderStatus;
import info.novatec.beantest.demo.persistence.OrderPersistenceService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Carlos Barragan
 * 
 */
@Stateless
public class OrderService {

    @EJB
    ShippingService shippingService;

    @EJB
    CustomerService customerService;

    @EJB
    BackEndOrderSystem backEndOrderSystem;

    @EJB
    OrderPersistenceService orderPersistenceService;

    public double calculateOrderPrice(Order order) {
        double shippingCost = shippingService.calculateShipping(order);
        double orderPrice = order.getOrderitems().stream()
                .mapToDouble(orderItem -> orderItem.getQuantity() * orderItem.getItem().getPrice())
                .sum();

        Customer customer = customerService.loadCustomerByCustomerId(order.getCustomerId());
        double total = shippingCost + orderPrice;
        // discount of 5% for vip non plus ultra
        double discount = customer.getCustomerStatus() == VIP_NON_PLUS_ULTRA ? 0.05 : 0;

        return total - (total * discount);
    }

    public void placeOrder(Order order) {
        if (order.getTotalPrice() == 0 || order.getOrderitems().isEmpty()
                || order.getShippingAddress() == null) {
            throw new IllegalStateException("Invalid order");
        }

        order.setOrderStatus(OrderStatus.PLACED);
        orderPersistenceService.save(order);

        backEndOrderSystem.placeOrder(order);
    }

}
