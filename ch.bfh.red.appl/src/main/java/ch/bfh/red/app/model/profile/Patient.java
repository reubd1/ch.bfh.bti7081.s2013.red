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

	@OneToMany(mappedBy = "patient", cascade=CascadeType.ALL)
	private Collection<DiaryEntry> diaryEntry;

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

}
