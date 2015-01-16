/**
 * 
 */
package info.novatec.beantest.demo.services;

import info.novatec.beantest.demo.entities.Customer;
import info.novatec.beantest.demo.entities.CustomerStatus;
import info.novatec.beantest.demo.entities.Order;

import java.util.Locale;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Carlos Barragan
 * 
 */
@Stateless
public class ShippingService {

    @EJB
    CustomerService customerService;

    /**
     * Calculates the shipping cost according to the following rules:
     * <p>
     * Shipping address is in Germany:<br>
     * Customer has VIP NON PLUS ULTRA Status -> No shipping costs.<br>
     * Customer has VIP Status, if customer ordered less than 6 items, then no
     * shipping costs. Otherwise €3.<br>
     * Customer has normal status, then shipping costs €4
     * <p>
     * Customer address is not in Germany:<br>
     * Customer has VIP NON PLUS ULTRA Status -> Shipping costs €3.<br>
     * Customer has no VIP NON PLUS ULTRA Status -> Shipping costs €5<br>
     * 
     * @param order
     * @return
     */
    public double calculateShipping(Order order) {
        Customer customer = customerService.loadCustomerByCustomerId(order.getCustomerId());
        CustomerStatus customerStatus = customer.getCustomerStatus();
        return customerStatus
                .reducedShippingWhen((cs -> cs.equals(CustomerStatus.VIP) ? atLeastFiveItemsIn(order)
                        : isShippingInGermany(order)));

    }

    private boolean isShippingInGermany(Order order) {
        return Locale.GERMANY.equals(order.getShippingAddress().getCountry());
    }

    private boolean atLeastFiveItemsIn(Order order) {
        return order.getTotalOrderedItems() <= 5;
    }
}
