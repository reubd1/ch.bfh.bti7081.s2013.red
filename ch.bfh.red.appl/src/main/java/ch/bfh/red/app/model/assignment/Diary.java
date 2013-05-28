package ch.bfh.red.app.model.assignment;

import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Diary extends Assignment {

	private String entry;

	public enum FeelingEnum {
		SUPER(5), NAJA(3), SCHLECHT(1);

		private Integer numericValue;

		private FeelingEnum(Integer nV) {
			this.numericValue = nV;
		}

		public Integer getNumericValue() {
			return numericValue;
		}
	}

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateTime;

	@Enumerated(EnumType.ORDINAL)
	private FeelingEnum feeling;

	public FeelingEnum getFeeling() {
		return feeling;
	}

	public void setFeeling(FeelingEnum feeling) {
		this.feeling = feeling;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public Calendar getDateTime() {
		return dateTime;
	}

	public void setDateTime(Calendar dateTime) {
		this.dateTime = dateTime;
	}

}
