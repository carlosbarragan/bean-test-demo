package info.novatec.beantest.demo.persistence;

import info.novatec.beantest.demo.entities.ShippingAddress;

import javax.ejb.Stateless;

@Stateless
public class AddressPersistenceService extends PersistenceService<ShippingAddress> {

	protected AddressPersistenceService() {
		super(ShippingAddress.class);
	}

}
