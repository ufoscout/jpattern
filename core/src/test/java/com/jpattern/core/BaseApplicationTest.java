package com.jpattern.core;

import java.io.File;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public abstract class BaseApplicationTest extends BaseTest {

	private SystemProvider systemProvider;
	private IApplication application;
	protected final static int MAIL_SERVER_PORT = 2048 + ((int)(Math.random() * 60000));

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		File path = new File("target/log");
		if (!path.exists()) {
			path.mkdirs();
		}
		
		systemProvider = new SystemProvider();
		application = new Application(systemProvider, "ApplicationTest" , MAIL_SERVER_PORT );
		application.startApplication();
		new SystemProxy(systemProvider);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		application.stopApplication();
	}

    public ISystem getSystem() {
        return systemProvider;
    }
    
    public IProvider getProvider() {
        return systemProvider;
    }    
}
