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


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
	public Collection<Medication> getMedication() {
		return medication;
	}

	public void setMedication(Collection<Medication> medication) {
		this.medication = medication;
	}

}
