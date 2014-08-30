package com.jpattern.gwt;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.jpattern.gwt.client.GwtTestSimple;
import com.jpattern.gwt.client.cache.CacheTestGwt;
import com.jpattern.gwt.client.command.CommandChainAsynchTestGWT;
import com.jpattern.gwt.client.command.impl.GetTextResourceCommandTestGwt;
import com.jpattern.gwt.client.communication.rest.DeleteCallCommandTestGwt;
import com.jpattern.gwt.client.communication.rest.DeleteProxyTestGwt;
import com.jpattern.gwt.client.communication.rest.GenericsObjectSerializerTestGwt;
import com.jpattern.gwt.client.communication.rest.GetCallCommandTestGwt;
import com.jpattern.gwt.client.communication.rest.GetProxyTestGwt;
import com.jpattern.gwt.client.communication.rest.PostCallCommandTestGwt;
import com.jpattern.gwt.client.communication.rest.PostProxyTestGwt;
import com.jpattern.gwt.client.communication.rest.PutCallCommandTestGwt;
import com.jpattern.gwt.client.communication.rest.PutProxyTestGwt;
import com.jpattern.gwt.client.history.FixedSizeHistoryManager2TestGwt;
import com.jpattern.gwt.client.history.FixedSizeHistoryManager3TestGwt;
import com.jpattern.gwt.client.history.FixedSizeHistoryManagerTestGwt;
import com.jpattern.gwt.client.serializer.AutoBeanSerializerServiceTestGwt;
import com.jpattern.gwt.client.spike.AutoBeanSerializerTestGwt;
import com.jpattern.gwt.client.util.QueryStringTestGwt;
import com.jpattern.gwt.client.view.AGwtCompositeViewTestGwt;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author Francesco Cina'
 *
 * 2 Feb 2011
 * 
 * This is a suite of all the functional tests of the application
 */
public class GwtSuiteTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public GwtSuiteTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		
		TestSuite suite = new GWTTestSuite("GWT TESTS SUITE FOR jpattern-gwt");
		
        suite.addTestSuite( GwtTestSimple.class );
        suite.addTestSuite( GetProxyTestGwt.class );
        suite.addTestSuite( PostProxyTestGwt.class );
        suite.addTestSuite( PutProxyTestGwt.class );
        suite.addTestSuite( DeleteProxyTestGwt.class );
        suite.addTestSuite( GetCallCommandTestGwt.class );
        suite.addTestSuite( DeleteCallCommandTestGwt.class );
        suite.addTestSuite( PostCallCommandTestGwt.class );
        suite.addTestSuite( PutCallCommandTestGwt.class );
        suite.addTestSuite( GetTextResourceCommandTestGwt.class );
        suite.addTestSuite( GenericsObjectSerializerTestGwt.class );
        suite.addTestSuite( QueryStringTestGwt.class );
        suite.addTestSuite( CacheTestGwt.class );
		suite.addTestSuite( FixedSizeHistoryManagerTestGwt.class );
		suite.addTestSuite( FixedSizeHistoryManager2TestGwt.class );
		suite.addTestSuite( FixedSizeHistoryManager3TestGwt.class );
		suite.addTestSuite( AutoBeanSerializerServiceTestGwt.class );
		suite.addTestSuite( CommandChainAsynchTestGWT.class );
		suite.addTestSuite( AutoBeanSerializerTestGwt.class );
		suite.addTestSuite( AGwtCompositeViewTestGwt.class );
		
        return suite;
		
	}
}
