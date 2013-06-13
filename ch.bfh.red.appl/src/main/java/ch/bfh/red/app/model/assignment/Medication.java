package ch.bfh.red.app.model.assignment;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * @author TEAM RED
 * 
 * 			Entity Medication
 */
@Entity
public class Medication extends AssignmentDataRange {

	public enum DosisUnitEnum {
		miligramm("mg"), pieces("stk"), mililiter("ml");

		private String shortForm;

		private DosisUnitEnum(String nV) {
			this.shortForm = nV;
		}

		public String getNumericValue() {
			return shortForm;
		}
	}

	private Long dosis;

	private Long intervalInHours;

	private Long stock;

	private String entry;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastIntake;

	@Enumerated(EnumType.ORDINAL)
	private DosisUnitEnum dosisUnit;

	@ManyToOne(optional = false)
	@JoinColumn(name = "medicine_fk")
	@NotNull
	private Medicine medicine;

	/**
	 * @return the dosisUnit
	 */
	public DosisUnitEnum getDosisUnit() {
		return dosisUnit;
	}

	/**
	 * @param dosisUnit
	 *            the dosisUnit to set
	 */
	public void setDosisUnit(DosisUnitEnum dosisUnit) {
		this.dosisUnit = dosisUnit;
	}

	/**
	 * @return the entry
	 */
	public String getEntry() {
		return entry;
	}

	/**
	 * @param entry
	 *            the entry to set
	 */
	public void setEntry(String entry) {
		this.entry = entry;
	}

	/**
	 * @return the dosis
	 */
	public Long getDosis() {
		return dosis;
	}

	/**
	 * @param dosis
	 *            the dosis to set
	 */
	public void setDosis(Long dosis) {
		this.dosis = dosis;
	}

	/**
	 * @return the intervalInHours
	 */
	public Long getIntervalInHours() {
		return intervalInHours;
	}

	/**
	 * @param intervalInHours
	 *            the intervalInHours to set
	 */
	public void setIntervalInHours(Long intervalInHours) {
		this.intervalInHours = intervalInHours;
	}

	/**
	 * @return the stock
	 */
	public Long getStock() {
		return stock;
	}

	/**
	 * @param stock
	 *            the stock to set
	 */
	public void setStock(Long stock) {
		this.stock = stock;
	}

	/**
	 * @return the medicine
	 */
	public Medicine getMedicine() {
		return medicine;
	}

	/**
	 * @param medicine
	 *            the medicine to set
	 */
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	//TODO move into logic/controller class
	public boolean isTimeForNextIntakeNow() {
		
		if(!super.isNowInRange()){
			return false;
		}
		
		if (lastIntake == null ){
			return true;
		}
		
		// now
		Calendar tmpCalc = Calendar.getInstance();
		
		// now - interval
		tmpCalc.add(Calendar.HOUR_OF_DAY, - intervalInHours.intValue());
		
		if(lastIntake.before(tmpCalc)){
			return true;
		}
		
		return false;
	}
	
	   /**
	 * @return the lastIntake
	 */
	public Calendar getLastIntake() {
		return lastIntake;
	}

	/**
	 * @param lastIntake the lastIntake to set
	 */
	public void setLastIntake(Calendar lastIntake) {
		this.lastIntake = lastIntake;
	}
}
