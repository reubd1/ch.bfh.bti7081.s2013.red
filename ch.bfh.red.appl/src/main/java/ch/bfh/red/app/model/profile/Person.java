/**
 * 
 */
package ch.bfh.red.app.model.profile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

/**
 * @author dimitri.haemmerli
 *
 */
//HINT have a look
//http://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development/Entities/Inheritance#Example:_Using_TABLE_PER_CLASS_with_.40Inheritance_annotation
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(min=2, max= 45)
	private String name;
		
	@Size(min=2, max= 45)
	private String firstname;
	
	private String Country;
	
	private int ZipCode;
	
	private String city;
	
	private String street;
	
	private String streetNumber;

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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public int getZipCode() {
		return ZipCode;
	}

	public void setZipCode(int zipCode) {
		ZipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}


}
