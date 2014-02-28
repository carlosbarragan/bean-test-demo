/**
 * 
 */
package info.novatec.beantest.demo.backend;

import info.novatec.beantest.demo.entities.Order;

import javax.ejb.Remote;

/**
 * @author cb
 * 
 */
@Remote
public interface BackEndOrderSystem {

    void placeOrder(Order order);

}
