package info.novatec.beantest.demo.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Carlos Barragan
 * 
 */
@Entity
public class Item extends BaseEntity {

    @NotNull
    @Size(min = 1)
    private String description;

    private double price;

    protected Item() {

    }

    /**
     * @param description
     * @param price
     */
    public Item(String description, double price) {
        if (price < 0.0) {
            throw new IllegalArgumentException("Price may not be negative");
        }

        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description may not be null or empty");
        }
        this.description = description;
        this.price = price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    public static Item create(String description, double price) {
        return new Item(description, price);
    }

}
