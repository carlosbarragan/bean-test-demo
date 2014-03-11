/**
 * 
 */
package info.novatec.beantest.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Carlos Barragan
 * 
 */
@Entity
public class Customer extends BaseEntity {

    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    @Size(min = 5)
    @Column(unique = true)
    private String customerId;

    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;

    protected Customer() {

    }

    /**
     * @param name
     * @param customerId
     * @param customerStatus
     */
    public Customer(String name, String customerId, CustomerStatus customerStatus) {
        this.name = name;
        this.customerId = customerId;
        this.customerStatus = customerStatus;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the customerStatus
     */
    public CustomerStatus getCustomerStatus() {
        return customerStatus;
    }

    /**
     * @param customerStatus
     *            the customerStatus to set
     */
    public void setCustomerStatus(CustomerStatus customerStatus) {
        this.customerStatus = customerStatus;
    }

}
