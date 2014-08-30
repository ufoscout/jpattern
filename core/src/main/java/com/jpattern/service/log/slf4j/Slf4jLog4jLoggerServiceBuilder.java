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
public class Slf4jLog4jLoggerServiceBuilder extends ASlf4jLoggerServiceBuilder {

	public Slf4jLog4jLoggerServiceBuilder(String configFile) {
		this(new NullLoggerServiceBuilder(), configFile);
	}
	
	public Slf4jLog4jLoggerServiceBuilder(IServiceBuilder<ILoggerService> loggerServiceBuilder, String configFile) {
		super(loggerServiceBuilder, new Log4jLoggerBackendConfigurator(configFile));
	}

}
