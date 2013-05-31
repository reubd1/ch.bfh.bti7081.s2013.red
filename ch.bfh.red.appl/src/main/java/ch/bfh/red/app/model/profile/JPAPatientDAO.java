/**
 * 
 */
package ch.bfh.red.app.model.profile;

import java.util.Collection;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author dimitri.haemmerli
 *
 */
@Named
public class JPAPatientDAO implements PatientDAO {

	@PersistenceContext
	protected EntityManager em;

	public Patient create() {

		return new Patient();
		
	}


	public Patient read(long id) {
		
		return em.find(Patient.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Patient> read() {

		return em.createQuery("SELECT p FROM Patient p").getResultList();
		
	}

//	@Transactional
//	public Patient update(Patient patient) {
//		
//		return em.merge(patient);
//	}
//
//	@Transactional	
//	public void delete(Patient patient) {
//		
//		patient = em.merge(patient);
//		em.remove(patient);
//
//	}

}
