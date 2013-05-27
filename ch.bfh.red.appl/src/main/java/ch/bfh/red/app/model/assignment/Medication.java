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

/**
 * @author TEAM RED
 * 
 */
@Entity
public class Medication {

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

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   private Long dosis;

   private Long intervalInHours;

   private Long stock;

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
   private DosisUnitEnum dosisUnit;

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

   public Calendar getDateTime() {
      return dateTime;
   }

   public void setDateTime(Calendar dateTime) {
      this.dateTime = dateTime;
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

}
