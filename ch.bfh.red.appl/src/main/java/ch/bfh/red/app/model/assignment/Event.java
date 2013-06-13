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
 *			Entity Event
 */
@Entity
public class Event extends AssignmentDataRange {
	
	private String name;
	
	private String location;
	
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "Patient_Event",
    joinColumns = {@JoinColumn(name = "Patient_FK")}, 
    inverseJoinColumns = {@JoinColumn(name = "Event_FK")})  
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
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the patients
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
