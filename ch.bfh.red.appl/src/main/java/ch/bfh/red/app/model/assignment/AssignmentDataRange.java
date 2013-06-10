package ch.bfh.red.app.model.assignment;

import java.util.Calendar;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author dimitri.haemmerli
 * 
 * 		Superclass for Entities with a start and end time <Medication, Event>	
 */

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AssignmentDataRange extends Assignment {

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar startDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar endDate;

	/**
	 * @return the startDate
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Calendar getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	
	/**
	 * @return the boolen isNowInRange 
	 */
	public boolean isNowInRange() {
		if (endDate == null || startDate == null) {
			return false;
		}

		Calendar now = Calendar.getInstance();

		if (startDate.before(now) && endDate.after(now)) {
			return true;
		}

		return false;
	}

}
