package info.novatec.beantest.demo.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Carlos Barragan
 * 
 */
@Entity
@Table(name = "Customer_Order")
@NamedQuery(name=Order.Queries.ALL_ORDERS_BY_CUSTOMER, query="Select o from Order o where o.customerId=:" + Order.Parameters.CUSTOMER_ID)
public class Order extends BaseEntity {
	
	public interface Queries {
		String ALL_ORDERS_BY_CUSTOMER = "Order.allOrdersByCustomer";
	}
	public interface Parameters {
		String CUSTOMER_ID="customerId";
	}

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    private ShippingAddress shippingAddress;

    @NotNull
    @Size(min = 5)
    private String customerId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.OPEN;

    private double totalPrice;

    /**
     * @return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice
     *            the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId
     *            the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the orderitems
     */
    public List<OrderItem> getOrderitems() {
        return orderItems;
    }

    /**
     * @param orderitems
     *            the orderitems to set
     */
    public void addOrderitem(OrderItem orderItem) {
        Objects.requireNonNull(orderItem, "orderitem may not be null");
        orderItem.setOrder(this);
        this.orderItems.add(orderItem);
    }

    /**
     * @return the shippingAddress
     */
    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    /**
     * @param shippingAddress
     *            the shippingAddress to set
     */
    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * @return the orderStatus
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus
     *            the orderStatus to set
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public int getTotalOrderedItems() {
    	return this.getOrderitems().stream().mapToInt(OrderItem::getQuantity).sum();
    }

}
