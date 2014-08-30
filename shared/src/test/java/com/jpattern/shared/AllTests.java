package com.jpattern.shared;

import com.jpattern.shared.util.NumberUtilTest;
import com.jpattern.shared.util.StringHelperTest;
import com.jpattern.shared.util.UniqueIdTest;
import com.jpattern.shared.util.ValueUtilTest;

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
        TestSuite suite = new TestSuite("TEST SUITE FOR jPattern shared");
        
        suite.addTestSuite( StringHelperTest.class );
        suite.addTestSuite( UniqueIdTest.class );
        suite.addTestSuite( NumberUtilTest.class );
        suite.addTestSuite( ValueUtilTest.class );
        
        return suite;
    }
}
