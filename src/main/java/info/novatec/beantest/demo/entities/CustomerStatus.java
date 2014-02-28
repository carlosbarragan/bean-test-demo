/**
 * 
 */
package info.novatec.beantest.demo.entities;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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

    public double reducedShippingExcept(Predicate<CustomerStatus> ifCustomerStatus,
            Supplier<Boolean> decisionSupplier) {

        if (ifCustomerStatus.test(this)) {
            return reducedShippingIf(decisionSupplier);
        } else {
            return reducedShipping;
        }
    }

    public double reducedShippingFunction(Function<CustomerStatus, Double> rsf) {
        return rsf.apply(this);
    }

    public double reducedShippingIf(Supplier<Boolean> decisionSupplier) {
        Objects.requireNonNull(decisionSupplier, "decisionSupplier may not be null");
        Objects.requireNonNull(decisionSupplier.get(), "decisionSupplier may not return null");
        if (decisionSupplier.get()) {
            return reducedShipping;
        } else {
            return normalShipping;
        }
    }

}
