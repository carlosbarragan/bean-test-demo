/**
 * 
 */
package info.novatec.beantest.demo.entities;

import java.util.Locale;

import javax.persistence.Entity;

/**
 * @author Carlos Barragan
 * 
 */
@Entity
public class ShippingAddress extends BaseEntity {

	private Locale country;

	private String street;

	private String zipCode;

	protected ShippingAddress() {
		// For JPA.
	}

	/**
	 * @param country
	 * @param street
	 * @param zipCode
	 */
	public ShippingAddress(Locale country, String street, String zipCode) {
		this.country = country;
		this.street = street;
		this.zipCode = zipCode;
	}

	/**
	 * @return the country
	 */
	public Locale getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(Locale country) {
		this.country = country;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
