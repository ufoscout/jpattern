package com.jpattern.service.log.slf4j;

import com.jpattern.core.IServiceBuilder;
import com.jpattern.service.log.ILoggerService;
import com.jpattern.service.log.NullLoggerServiceBuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 4 Mar 2011
 */
public class Slf4jLoggerServiceBuilder extends ASlf4jLoggerServiceBuilder {

	public Slf4jLoggerServiceBuilder() {
		this(new NullLoggerBackendConfigurator());
	}
	
	public Slf4jLoggerServiceBuilder(ILoggerBackendConfigurator loggerBackendConfigurator) {
		this(new NullLoggerServiceBuilder(), loggerBackendConfigurator);
	}
	
	public Slf4jLoggerServiceBuilder(IServiceBuilder<ILoggerService> loggerServiceBuilder, ILoggerBackendConfigurator loggerBackendConfigurator) {
		super(loggerServiceBuilder, loggerBackendConfigurator);
	}

}