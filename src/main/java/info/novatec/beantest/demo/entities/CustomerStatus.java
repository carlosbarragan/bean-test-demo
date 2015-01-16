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

    private final double normalShippingCost;
    private final double reducedShippingCost;

    /**
     * @param normalShippingCost
     * @param reducedShippingCost
     */
    private CustomerStatus(double normalShipping, double reducedShipping) {
        this.normalShippingCost = normalShipping;
        this.reducedShippingCost = reducedShipping;
    }

    /**
     * @return the normalShipping
     */
    public double getNormalShipping() {
        return normalShippingCost;
    }

    /**
     * @return the reducedShipping
     */
    public double getReducedShipping() {
        return reducedShippingCost;
    }

    public double reducedShippingWhen(Function<CustomerStatus, Boolean> rsf) {
        return rsf.apply(this) ? reducedShippingCost : normalShippingCost;
    }

}
