package ch.bfh.red.app.model.assignment;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import ch.bfh.red.app.model.profile.Patient;

@Entity
public class DiaryEntry extends Assignment {

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

	@Enumerated(EnumType.ORDINAL)
//	@NotNull
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

}
