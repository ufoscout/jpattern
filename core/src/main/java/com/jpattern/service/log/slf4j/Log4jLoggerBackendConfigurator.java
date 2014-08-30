package com.jpattern.service.log.slf4j;

import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author Francesco Cina'
 *
 * 5 Apr 2011
 */
public class Log4jLoggerBackendConfigurator implements ILoggerBackendConfigurator {

	private static final long serialVersionUID = 1L;
	private final String configFile;

	public Log4jLoggerBackendConfigurator(String configFile) {
		this.configFile = configFile;
	}
	
	@Override
	public void configure() {
		PropertyConfigurator.configure(configFile);
	}

}
