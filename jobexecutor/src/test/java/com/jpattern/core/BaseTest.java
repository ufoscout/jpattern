package com.jpattern.core;

import com.jpattern.jobexecutor.AThreadExecutorCommand;

import junit.framework.TestCase;

/**
 * 
 * @author Francesco Cina'
 *
 * 20/nov/2009
 */
public abstract class BaseTest extends TestCase {

	public static String TEST_FILES_BASE_PATH = "./src/test/files";
    
    public BaseTest() {
    }

    public BaseTest(String name) {
        super(name);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        
        AThreadExecutorCommand.FORCE_SHUTDOWN_WITH_SYSTEMEXIT0 = false;
        
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