package ch.bfh.red.app.model.assignment;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import ch.bfh.red.app.model.profile.Patient;

/**
 * @author TEAM RED
 * 
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

   @Enumerated(EnumType.ORDINAL)
   private DosisUnitEnum dosisUnit;


   @ManyToOne(optional = false)
   @JoinColumn(name = "medicine_fk")
   @NotNull
   private Medicine medicine;

   
   public DosisUnitEnum getDosisUnit() {
      return dosisUnit;
   }

   public void setDosisUnit(DosisUnitEnum dosisUnit) {
      this.dosisUnit = dosisUnit;
   }

   public String getEntry() {
      return entry;
   }

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
    *           the dosis to set
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
    *           the intervalInHours to set
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
    *           the stock to set
    */
   public void setStock(Long stock) {
      this.stock = stock;
   }

   public Medicine getMedicine() {
	   return medicine;
   }

   public void setMedicine(Medicine medicine) {
	   this.medicine = medicine;
   }


}
