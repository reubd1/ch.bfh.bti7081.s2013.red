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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Collection<Patient> getPatients() {
		return patients;
	}


	public void setPatients(Collection<Patient> patients) {
		this.patients = patients;
	}

	
}
