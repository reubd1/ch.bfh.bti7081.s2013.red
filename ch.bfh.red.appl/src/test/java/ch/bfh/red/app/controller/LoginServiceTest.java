/**
 * 
 */
package ch.bfh.red.app.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ch.bfh.red.app.model.profile.Patient;

import com.vaadin.server.VaadinSession;

/**
 * @author stola
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

	/**
	 * Test method for {@link ch.bfh.red.app.controller.LoginService#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		// make sure that we get always the same instance
		LoginService ls1 = LoginService.getInstance();
		LoginService ls2 = LoginService.getInstance();

		assertNotNull(ls1);
		assertNotNull(ls2);
		assertEquals(ls1, ls2);
	}

	/**
	 * Test method for
	 * {@link ch.bfh.red.app.controller.LoginService#doLogin(com.vaadin.server.VaadinSession, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testDoLogin_NOK_null_values() {
		LoginService loginService = LoginService.getInstance();

		assertFalse("do login should return false, if it was not successful", loginService.doLogin(null, null, null));
	}

	/**
	 * Test method for
	 * {@link ch.bfh.red.app.controller.LoginService#doLogin(com.vaadin.server.VaadinSession, java.lang.String, java.lang.String)}
	 * .
	 */
	// @Test
	// public void testDoLogin_OK() {
	// LoginService loginService = LoginService.getInstance();
	//
	// VaadinSession ses = mock(VaadinSession.class);
	// when(ses.getAttribute("")).thenReturn(null);
	//
	// assertTrue(loginService.doLogin(ses, "mario", "mario"));
	//
	// }

	/**
	 * Test method for
	 * {@link ch.bfh.red.app.controller.LoginService#doLogout(com.vaadin.server.VaadinSession)}.
	 */
	@Test
	public void testDoLogout() {
		// prepare mock data
		VaadinSession ses = mock(VaadinSession.class);
		Boolean loggedIn = true;

		when(ses.getAttribute("USR_LOGGED_IN")).thenReturn(loggedIn);
		LoginService loginService = LoginService.getInstance();

		// 1 preckeck, is user logged in?
		assertTrue(loginService.isLoggedIn(ses));

		// TODO try to mock setAttribute, but how?
		// // 2 do logout
		// fail("Not possible to mock... Or?");
		// loginService.doLogout(ses);
		//
		// // 3 post check, is user relly logged out?
		// assertFalse(loginService.isLoggedIn(ses));

	}

	/**
	 * Test method for
	 * {@link ch.bfh.red.app.controller.LoginService#isLoggedIn(com.vaadin.server.VaadinSession)}.
	 */
	@Test
	public void testIsLoggedIn() {
		LoginService loginService = LoginService.getInstance();
		VaadinSession ses = mock(VaadinSession.class);

		// NOK => not logged in => initial state
		when(ses.getAttribute("USR_LOGGED_IN")).thenReturn(null);
		assertFalse(loginService.isLoggedIn(ses));

		// NOK => not logged in
		when(ses.getAttribute("USR_LOGGED_IN")).thenReturn(false);
		assertFalse(loginService.isLoggedIn(ses));

		// OK => user logged in
		when(ses.getAttribute("USR_LOGGED_IN")).thenReturn(true);
		assertTrue(loginService.isLoggedIn(ses));
	}

	/**
	 * Test method for
	 * {@link ch.bfh.red.app.controller.LoginService#getLoggedInUser(com.vaadin.server.VaadinSession)}
	 * .
	 */
	@Test
	public void testGetLoggedInUser() {
		// prepare mock data
		VaadinSession ses = mock(VaadinSession.class);
		Patient mockP = new Patient();
		mockP.setName("Mockito");
		mockP.setFirstname("Peter");
		mockP.setCity("Nevercity");

		LoginService loginService = LoginService.getInstance();

		// NOK => not logged in, patient NULL
		when(ses.getAttribute("USR_LOGGED_IN_USR")).thenReturn(null);
		assertNull(loginService.getLoggedInUser(ses));

		// OK => user logged in
		when(ses.getAttribute("USR_LOGGED_IN_USR")).thenReturn(mockP);
		Object o = loginService.getLoggedInUser(ses);
		assertNotNull(o);
		Patient patientGotBack = (Patient) o;
		assertNotNull(patientGotBack);
		assertEquals("Mockito", patientGotBack.getName());
		assertEquals("Peter", patientGotBack.getFirstname());
		assertEquals("Nevercity", patientGotBack.getCity());
	}

}
