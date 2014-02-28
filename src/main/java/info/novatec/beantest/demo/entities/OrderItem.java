/**
 * 
 */
package info.novatec.beantest.demo.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author Carlos Barragan
 * 
 */
@Entity
public class OrderItem extends BaseEntity {

    @ManyToOne
    private Order order;

    @OneToOne
    private Item item;

    private int quantity;

    protected OrderItem() {

    }

    /**
     * @param order
     * @param item
     */
    public OrderItem(Item item) {
        this.item = item;
    }

    /**
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order
     *            the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item
     *            the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderItem withQuantity(int quantity) {
        this.setQuantity(quantity);
        return this;
    }

}
