/**
 * 
 */
package info.novatec.beantest.demo.entities;

import java.util.function.Function;

/**
 * @author cb
 * 
 */
public enum CustomerStatus {
    NORMAL(5, 4), VIP(3, 0), VIP_NON_PLUS_ULTRA(3, 0);

    private final double normalShipping;
    private final double reducedShipping;

    /**
     * @param normalShipping
     * @param reducedShipping
     */
    private CustomerStatus(double normalShipping, double reducedShipping) {
        this.normalShipping = normalShipping;
        this.reducedShipping = reducedShipping;
    }

    /**
     * @return the normalShipping
     */
    public double getNormalShipping() {
        return normalShipping;
    }

    /**
     * @return the reducedShipping
     */
    public double getReducedShipping() {
        return reducedShipping;
    }

    public double reducedShippingWhen(Function<CustomerStatus, Boolean> rsf) {
        return rsf.apply(this) ? reducedShipping : normalShipping;
    }

}
