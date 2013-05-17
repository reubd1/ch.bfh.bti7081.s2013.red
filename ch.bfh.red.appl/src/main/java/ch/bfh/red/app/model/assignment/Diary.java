package ch.bfh.red.app.model.assignment;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Diary {

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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String entry;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
