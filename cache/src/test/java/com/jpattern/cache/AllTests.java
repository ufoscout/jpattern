package com.jpattern.cache;

import com.jpattern.cache.ehcache.EhCacheServiceTest;
import com.jpattern.cache.simple.CacheTest;
import com.jpattern.cache.simple.PermanentCacheTest;

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
        TestSuite suite = new TestSuite("TEST SUITE FOR jPattern Framework");
        
        suite.addTestSuite( EhCacheServiceTest.class );
        suite.addTestSuite( PermanentCacheTest.class );
        suite.addTestSuite( CacheTest.class );
        
        return suite;
    }
}
