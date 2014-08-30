package com.jpattern.service.log.slf4j;

import com.jpattern.core.IServiceBuilder;
import com.jpattern.service.log.ILoggerService;

/**
 * 
 * @author Francesco Cina'
 *
 * 4 Mar 2011
 */
public abstract class ASlf4jLoggerServiceBuilder implements IServiceBuilder<ILoggerService> {

	private final ILoggerBackendConfigurator loggerBackendConfigurator;
	private final IServiceBuilder<ILoggerService> loggerServiceBuilder;
	
	public ASlf4jLoggerServiceBuilder(IServiceBuilder<ILoggerService> loggerServiceBuilder, ILoggerBackendConfigurator loggerBackendConfigurator) {
		this.loggerServiceBuilder = loggerServiceBuilder;
		this.loggerBackendConfigurator = loggerBackendConfigurator;
	}

	@Override
	public final ILoggerService buildService() {
		loggerBackendConfigurator.configure();
		return new Slf4jLoggerService( loggerServiceBuilder.buildService() );
	}

}
