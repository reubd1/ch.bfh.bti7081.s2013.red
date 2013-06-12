/**
 * 
 */
package ch.bfh.red.app.model.profile;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import ch.bfh.red.app.model.assignment.DiaryEntry;
import ch.bfh.red.app.model.assignment.Event;
import ch.bfh.red.app.model.assignment.Medication;


/**
 * @author dimitri.haemmerli
 *
 */
@Entity
public class Patient extends Person{
	
	@NotNull
	@Column(columnDefinition="Integer default 0")
    @Min(1)
	@Max(5)
	private Integer independenceLevel;
	
	private String loginName;
	
	private String loginPassword;

	@OneToMany(mappedBy = "patient", cascade=CascadeType.ALL)
	private Collection<DiaryEntry> diaryEntry;
	
	@OneToMany(mappedBy = "patient", cascade=CascadeType.ALL)
	private Collection<Medication> medication;

    @ManyToMany(mappedBy = "patients",cascade=CascadeType.ALL)      
    private Collection<Event> contacts;
   
    @ManyToMany(mappedBy = "patients",cascade=CascadeType.ALL)      
    private Collection<Event> events;
	
	public Integer getIndependenceLevel() {
		return independenceLevel;
	}

	public void setIndependenceLevel(Integer independenceLevel) {
		this.independenceLevel = independenceLevel;
	}

	
	 public Collection<DiaryEntry> getDiaryEntry() {
		return diaryEntry;
	}

	public void setDiaryEntry(Collection<DiaryEntry> diaryEntry) {
		this.diaryEntry = diaryEntry;
	}

	public Collection<Event> getContacts() {
		return contacts;
	}

	public void setContacts(Collection<Event> contacts) {
		this.contacts = contacts;
	}

	public Collection<Event> getEvents() {
		return events;
	}

	public void setEvents(Collection<Event> events) {
		this.events = events;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the loginPassword
	 */
	public String getLoginPassword() {
		return loginPassword;
	}

	/**
	 * @param loginPassword the loginPassword to set
	 */
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public Collection<Medication> getMedication() {
		return medication;
	}

	public void setMedication(Collection<Medication> medication) {
		this.medication = medication;
	}

}
