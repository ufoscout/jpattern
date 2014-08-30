package com.jpattern.gwt.client;

import java.math.BigDecimal;

import com.jpattern.gwt.client.AApplication;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.IApplicationSystem;
import com.jpattern.gwt.client.NullServerCallService;
import com.jpattern.gwt.client.logger.NullLoggerService;

import junit.framework.TestCase;

/**
 * 
 * @author Francesco Cina' 08/nov/2010 - 12.18.24
 *
 */
public abstract class BaseTest extends TestCase {
	
	private String testFilesInputBasePath = "./src/test/files";
	
	private static AApplication APPLICATION; 
	
	Chronometer cronometro = new Chronometer();
    
    public BaseTest() {
    }

    protected void setUp() throws Exception {
        super.setUp();
        
        if (APPLICATION==null) {
			APPLICATION = new Application();
			APPLICATION.startSystem();
			assertFalse( APPLICATION.getLoggerService() instanceof NullLoggerService );
			APPLICATION.setServerCallService( new NullServerCallService() );
			APPLICATION.getLoggerService().getLogger(this.getClass()).info("gwtSetUp", "Application for tests started");
        }
        cronometro.avanzaDaCapo();
        
        System.out.println("===================================================================");
        System.out.println("BEGIN TEST " + getName());
        System.out.println("===================================================================");
    }


	protected void tearDown() throws Exception {
        super.tearDown();

        cronometro.ferma();
        String time = new BigDecimal(cronometro.leggi() ).divide(new BigDecimal(1000)).toString();
        
        System.out.println("===================================================================");
        System.out.println("END TEST " + getName());
        System.out.println("Execution time: " + time + " seconds");
        System.out.println("===================================================================");
    }
	
	
	protected IApplicationProvider getProvider() {
		return APPLICATION;
	}
	
	protected IApplicationSystem getSystem() {
		return APPLICATION;
	}
	
	protected String getTestInputBasePath() throws Exception {
		return testFilesInputBasePath;
	}
	
}