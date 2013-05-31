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
 */

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AssignmentDataRange extends Assignment {

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar startDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar endDate;

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	
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
