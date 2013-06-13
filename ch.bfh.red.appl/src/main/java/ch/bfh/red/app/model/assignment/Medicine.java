/**
 * 
 */
package ch.bfh.red.app.model.assignment;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author dimitri.haemmerli
 * 
 *			Entity Medicine
 */
@Entity
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	
	private String description;
	
	@OneToMany(mappedBy = "medicine")
	private Collection<Medication> medication;


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 * 		the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * 
	 * 		the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 * 	
	 * 		the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}	
	
	/**
	 * @return medication
	 */
	public Collection<Medication> getMedication() {
		return medication;
	}

	/**
	 * @param medication
	 * 
	 * 		the medication to set
	 */
	public void setMedication(Collection<Medication> medication) {
		this.medication = medication;
	}

}
