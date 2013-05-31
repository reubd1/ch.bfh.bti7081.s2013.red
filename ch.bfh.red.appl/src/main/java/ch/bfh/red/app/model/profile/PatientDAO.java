/**
 * 
 */
package ch.bfh.red.app.model.profile;

import java.util.Collection;


/**
 * @author dimitri.haemmerli
 *
 */
public interface PatientDAO {

	public Patient create();
	
	public Patient read(long id);
	
	public Collection<Patient> read();
	
//	public Patient update(Patient patient);
//	
//	public void delete(Patient patient);

}
