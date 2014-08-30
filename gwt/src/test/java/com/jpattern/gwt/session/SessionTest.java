package com.jpattern.gwt.session;

import java.util.ArrayList;
import java.util.List;

import com.jpattern.gwt.client.BaseTest;
import com.jpattern.gwt.client.session.ISecurityContextObserver;
import com.jpattern.gwt.client.session.IUserData;
import com.jpattern.gwt.client.session.SecurityContext;
import com.jpattern.gwt.client.session.Session;
import com.jpattern.gwt.client.session.UserData;

/**
 * 
 * @author cinafr
 *
 */
public class SessionTest extends BaseTest {

	private int userLogin = 0;
	private int userLogout = 0;
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSecurity() throws Exception {
		SecurityContext securityContext = new SecurityContext();
		
		String role = null;
		List<String> roles = null;
		String[] rolesArray = null;
		assertFalse( securityContext.isUserInRole("role") );
		assertFalse( securityContext.isUserInRole(role) );
		assertFalse( securityContext.isUserInRole(roles) );
		assertFalse( securityContext.isUserInRole(rolesArray) );
		assertTrue( securityContext.isUserInRole(new ArrayList<String>()) );
		assertTrue( securityContext.isUserInRole(new String[0]) );
		
		
		IUserData userSession = new UserData("", true);
		securityContext.setUserData(userSession);
		assertFalse( securityContext.isUserInRole("role") );
		assertFalse( securityContext.isUserInRole(role) );
		assertFalse( securityContext.isUserInRole(roles) );
		assertFalse( securityContext.isUserInRole(rolesArray) );
		assertTrue( securityContext.isUserInRole(new ArrayList<String>()) );
		assertTrue( securityContext.isUserInRole(new String[0]) );
		
		
		userSession.getRoles().add("role");
		securityContext.setUserData(userSession);
		assertTrue( securityContext.isUserInRole("role") );
		assertFalse( securityContext.isUserInRole("role-no") );
		assertFalse( securityContext.isUserInRole(role) );
		assertFalse( securityContext.isUserInRole(roles) );
		assertFalse( securityContext.isUserInRole(rolesArray) );
		assertTrue( securityContext.isUserInRole(new ArrayList<String>()) );
		assertTrue( securityContext.isUserInRole(new String[0]) );
		
		
		roles = new ArrayList<String>();
		roles.add("role-no");
		rolesArray = new String[]{"role-no"};
		assertFalse( securityContext.isUserInRole(roles) );
		assertFalse( securityContext.isUserInRole(rolesArray) );
		
		
		roles = new ArrayList<String>();
		roles.add("role");
		rolesArray = new String[]{"role"};
		assertTrue( securityContext.isUserInRole(roles) );
		assertTrue( securityContext.isUserInRole(rolesArray) );
		
		
		roles = new ArrayList<String>();
		roles.add("role");
		roles.add("role-no");
		rolesArray = new String[]{"role", "role-no"};
		assertTrue( securityContext.isUserInRole(roles) );
		assertTrue( securityContext.isUserInRole(rolesArray) );
	}
	
	public void testSecurityObserver() throws Exception {
		Session session = new Session();
		
		int login = 0;
		int logout = 0;
		ISecurityContextObserver observer = new Observer();
		session.addObserver(observer);
		assertEquals(login, userLogin);
		assertEquals(logout, userLogout);

		
		session.login(new UserData("", false));
		assertEquals(++login, userLogin);
		
		session.logout();
		session.login(new UserData("", false));
		assertEquals(++login, userLogin);
		assertEquals(++logout, userLogout);
		
		session.removeObserver(observer);
		session.logout();
		session.login(new UserData("", false));
		session.login(new UserData("", false));
		assertEquals(login, userLogin);
		assertEquals(logout, userLogout);
		
		session.addObserver(observer);
		session.logout();
		session.login(new UserData("", false));
		assertEquals(++login, userLogin);
		assertEquals(++logout, userLogout);
	}
	
	
	class Observer implements ISecurityContextObserver {

		@Override
		public void onUserLogin(IUserData userData) {
			userLogin++;
		}

		@Override
		public void onUserLogout(IUserData userData) {
			userLogout++;		
		}
		
	}
}
