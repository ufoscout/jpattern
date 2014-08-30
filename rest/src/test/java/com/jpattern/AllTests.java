package com.jpattern;

import com.jpattern.rest.RestGetDeleteActionExecutorTest;
import com.jpattern.rest.RestServletConfigurationTest;
import com.jpattern.rest.command.BuildQueryMapCommandTest;
import com.jpattern.rest.command.JsonCommandTest;
import com.jpattern.rest.command.ObjectToJsonStreamCommandTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/feb/2010
 */
public class AllTests extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AllTests( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite("TEST SUITE FOR jPattern Rest Framework");
        
        suite.addTestSuite( JsonCommandTest.class );
        suite.addTestSuite( ObjectToJsonStreamCommandTest.class );
        suite.addTestSuite( RestServletConfigurationTest.class );
        suite.addTestSuite( BuildQueryMapCommandTest.class );
        suite.addTestSuite( RestGetDeleteActionExecutorTest.class );
        
        return suite;
    }
}
