package com.jpattern.shared;

import junit.framework.TestCase;

/**
 * 
 * @author Francesco Cina'
 *
 * 20/nov/2009
 */
public abstract class BaseTest extends TestCase {

    public BaseTest() {
    }

    public BaseTest(String name) {
        super(name);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        
        System.out.println("===================================================================");
        System.out.println("BEGIN TEST " + getName());
        System.out.println("===================================================================");

    }


	protected void tearDown() throws Exception {
        super.tearDown();
        
        System.out.println("===================================================================");
        System.out.println("END TEST " + getName());
        System.out.println("===================================================================");
    }
	
}