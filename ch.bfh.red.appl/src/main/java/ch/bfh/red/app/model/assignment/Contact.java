/**
 * 
 */
package ch.bfh.red.app.model.assignment;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import ch.bfh.red.app.model.profile.Patient;

/**
 * @author dimitri.haemmerli
 *
 */
@Entity
public class Contact extends Assignment {

	private String name;
	
	private String firstname;
	
	private String phoneNumber;

	
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "Patient_Contact",
    joinColumns = {@JoinColumn(name = "Patient_FK")}, 
    inverseJoinColumns = {@JoinColumn(name = "Contact_FK")})  
    private Collection<Patient> patients;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param fistname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phonenumber
	 *            the phonenumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	/**
	 * @return a collection of patients
	 */
	public Collection<Patient> getPatients() {
		return patients;
	}

	/**
	 * @param patients
	 *            the patients to set
	 */
	public void setPatients(Collection<Patient> patients) {
		this.patients = patients;
	}

	
}
