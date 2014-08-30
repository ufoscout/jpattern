package com.jpattern.gwt;

import com.jpattern.gwt.bus.BusServiceTest;
import com.jpattern.gwt.cache.CacheTest;
import com.jpattern.gwt.client.communication.direct.DirectServerCallServiceTest;
import com.jpattern.gwt.command.CommandChainAsynchTest;
import com.jpattern.gwt.command.CommandChainSynchTest;
import com.jpattern.gwt.event.AEvent2Test;
import com.jpattern.gwt.event.AEventTest;
import com.jpattern.gwt.history.FixedSizeHistoryManager2Test;
import com.jpattern.gwt.history.FixedSizeHistoryManager3Test;
import com.jpattern.gwt.history.FixedSizeHistoryManager4Test;
import com.jpattern.gwt.history.FixedSizeHistoryManager5Test;
import com.jpattern.gwt.history.FixedSizeHistoryManager6Test;
import com.jpattern.gwt.history.FixedSizeHistoryManager7Test;
import com.jpattern.gwt.history.FixedSizeHistoryManagerTest;
import com.jpattern.gwt.presenter.APresenterSecurityTest;
import com.jpattern.gwt.property.PropertyReaderTest;
import com.jpattern.gwt.property.PropertyTest;
import com.jpattern.gwt.session.SessionAttributesTest;
import com.jpattern.gwt.session.SessionTest;
import com.jpattern.gwt.util.ClassNameTest;
import com.jpattern.spike.EscapeTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author Francesco Cina'
 *
 * 2 Feb 2011
 * 
 * This is a suite of all the unit tests of the application
 */
public class AllUnitTests extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AllUnitTests(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite("UNIT TESTS SUITE FOR jpattern-gwt");
		
		suite.addTestSuite( EscapeTest.class );
		suite.addTestSuite( CommandChainSynchTest.class );
		suite.addTestSuite( CommandChainAsynchTest.class );
		suite.addTestSuite( AEventTest.class );
		suite.addTestSuite( PropertyTest.class );
		suite.addTestSuite( PropertyReaderTest.class );
		suite.addTestSuite( DirectServerCallServiceTest.class );
		suite.addTestSuite( ClassNameTest.class );
		suite.addTestSuite( CacheTest.class );
		suite.addTestSuite( FixedSizeHistoryManagerTest.class );
		suite.addTestSuite( FixedSizeHistoryManager2Test.class );
		suite.addTestSuite( FixedSizeHistoryManager3Test.class );
		suite.addTestSuite( FixedSizeHistoryManager4Test.class );
		suite.addTestSuite( FixedSizeHistoryManager5Test.class );
		suite.addTestSuite( FixedSizeHistoryManager6Test.class );
		suite.addTestSuite( FixedSizeHistoryManager7Test.class );
		suite.addTestSuite( AEvent2Test.class );
		suite.addTestSuite( SessionTest.class );
		suite.addTestSuite( SessionAttributesTest.class );
		suite.addTestSuite( APresenterSecurityTest.class );
		suite.addTestSuite( BusServiceTest.class );
		
		return suite;
	}
}
