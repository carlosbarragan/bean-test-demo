/**
 * 
 */
package info.novatec.beantest.demo.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Carlos Barragan
 * 
 */
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue
	private long id;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

}
