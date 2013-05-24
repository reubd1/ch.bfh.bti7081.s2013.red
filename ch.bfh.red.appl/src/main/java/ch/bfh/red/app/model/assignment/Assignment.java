/**
 * 
 */
package ch.bfh.red.app.model.assignment;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author stola
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Assignment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastModifiedDate;

	private Integer triggerIntervalInSec;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the createdDate
	 */
	public Calendar getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public Calendar getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate
	 *            the lastModifiedDate to set
	 */
	public void setLastModifiedDate(Calendar lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * @return the triggerIntervalInSec
	 */
	public Integer getTriggerIntervalInSec() {
		return triggerIntervalInSec;
	}

	/**
	 * @param triggerIntervalInSec
	 *            the triggerIntervalInSec to set
	 */
	public void setTriggerIntervalInSec(Integer triggerIntervalInSec) {
		this.triggerIntervalInSec = triggerIntervalInSec;
	}

}
