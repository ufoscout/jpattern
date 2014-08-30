package com.jpattern.gwt.session;

import com.jpattern.gwt.client.BaseTest;
import com.jpattern.gwt.client.session.ISessionObserver;
import com.jpattern.gwt.client.session.Session;

/**
 * 
 * @author cinafr
 *
 */
public class SessionAttributesTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	public void testSecurityObserver() throws Exception {
		Session session = new Session();
		
		Observer observer = new Observer();
		session.addObserver(observer);
		
		assertEquals( "" , observer.lastAddedAttribute );
		assertEquals( "" , observer.lastRemovedAttribute );
		assertFalse( observer.sessionClean );
		
		session.addAttribute("key", "value");
		assertEquals( "key" , observer.lastAddedAttribute );
		assertNotNull( session.getAttribute("key", String.class) );
		
		session.removeAttribute("key");
		assertEquals( "key" , observer.lastRemovedAttribute );
		assertNull( session.getAttribute("key", String.class) );
		
		session.addAttribute("key1", "value");
		assertNotNull( session.getAttribute("key1", String.class) );
		session.cleanSession();
		assertTrue( observer.sessionClean );
		assertNull( session.getAttribute("key1", String.class) );
		
		session.removeObserver(observer);
		
		session.addAttribute("key2", "value");
		assertEquals( "key1" , observer.lastAddedAttribute );
		assertNotNull( session.getAttribute("key2", String.class) );
		
		session.removeAttribute("key2");
		assertEquals( "key" , observer.lastRemovedAttribute );
		assertNull( session.getAttribute("key2", String.class) );
		
		session.cleanSession();
	}
	
	
	class Observer implements ISessionObserver {

		String lastAddedAttribute = "";
		String lastRemovedAttribute = "";
		boolean sessionClean = false;
		
		@Override
		public void onSessionClean() {
			sessionClean = true;			
		}

		@Override
		public void onAttributeAdded(String key) {
			lastAddedAttribute = key;
		}

		@Override
		public void onAttributeRemoved(String key) {
			lastRemovedAttribute = key;			
		}

		
	}
}
