package ch.bfh.red.app.model.assignment;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author stola
 * 
 *         Superclass for all Entities<br>
 */
// HINT have a look
// http://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development/Entities/Inheritance#Example:_Using_TABLE_PER_CLASS_with_.40Inheritance_annotation
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Assignment {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
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

	@PrePersist
	void createdDate() {

		this.createdDate = Calendar.getInstance();
	}

	@PreUpdate
	void updatedAt() {

		this.lastModifiedDate = Calendar.getInstance();

	}

}
