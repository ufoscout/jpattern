package com.jpattern.ioc;

import junit.framework.TestCase;

public abstract class BaseTest extends TestCase {

    protected final String TEST_FILE = "./applconf/test.xml";
    protected final String TEST__PROPERTY_FILE = "./applconf/test.properties";
    
    protected void setUp() throws Exception {
        
        super.setUp();
        
        System.out.println("===================================================================");
        System.out.println("INIZIO TEST " + getName());
        System.out.println("===================================================================");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        
        System.out.println("===================================================================");
        System.out.println("FINE TEST " + getName());
        System.out.println("===================================================================");
    }

    
}
