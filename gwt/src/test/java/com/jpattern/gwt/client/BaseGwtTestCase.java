package com.jpattern.gwt.client;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.jpattern.gwt.client.AApplication;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.IApplicationSystem;
import com.jpattern.gwt.client.RestServerCallService;
import com.jpattern.gwt.client.logger.NullLoggerService;
import com.jpattern.gwt.client.serializer.AutoBeanSerializerService;
import com.jpattern.gwt.client.serializer.IBeanFactory;
import com.jpattern.gwt.shared.ITestBeanFactory;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public abstract class BaseGwtTestCase extends GWTTestCase {
	
	private static AApplication APPLICATION; 
	Chronometer cronometro = new Chronometer();
    
	protected final void gwtSetUp() throws Exception {
        super.gwtSetUp();
        
        if (APPLICATION==null) {
			APPLICATION = new Application();
			APPLICATION.startSystem();
			assertFalse( APPLICATION.getLoggerService() instanceof NullLoggerService );
			APPLICATION.setServerCallService( new RestServerCallService(  ));
			APPLICATION.setSerializerService( new AutoBeanSerializerService<IBeanFactory>( (IBeanFactory) GWT.create(ITestBeanFactory.class)) );
			APPLICATION.getLoggerService().getLogger(this.getClass()).info("gwtSetUp", "Application for tests started");
        }
        
        gwtTestCaseSetUp();
        cronometro.avanzaDaCapo();
        
        System.out.println("===================================================================");
        System.out.println("BEGIN GWT TEST " + getName());
        System.out.println("===================================================================");
    }


	protected IBeanFactory getBeanFactory() {
		return (IBeanFactory) GWT.create(ITestBeanFactory.class);
	}


	protected final void gwtTearDown() throws Exception {
		gwtTestCaseTearDown();
        super.gwtTearDown();

        cronometro.ferma();
        String time = new BigDecimal(cronometro.leggi() ).divide(new BigDecimal(1000)).toString();
        
        System.out.println("===================================================================");
        System.out.println("END GWT TEST " + getName());
        System.out.println("Execution time: " + time + " seconds");
        System.out.println("===================================================================");
    }

	protected abstract void gwtTestCaseSetUp() throws Exception;
		
	protected abstract void gwtTestCaseTearDown() throws Exception;
	
	protected IApplicationProvider getProvider() {
		return APPLICATION;
	}
	
	protected IApplicationSystem getSystem() {
		return APPLICATION;
	}
	
	protected void waitFor(long ms) {
		long startTimestamp = new Date().getTime();
		while (true) {
			if ( new Date().getTime() > (startTimestamp + ms) ) {
				return;
			}
		}
	}
	
	@Override
	public String getModuleName() {
		return "com.jpattern.gwt.jpattern_tests";
	}
}