package com.jpattern.core;

import com.jpattern.service.log.ILoggerService;
import com.jpattern.service.log.reader.ILoggerReaderService;
import com.jpattern.service.mail.IMailService;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 */
public interface ISystem extends IProvider {

	void setLoggerReaderServiceBuilder(IServiceBuilder<ILoggerReaderService> loggerReaderServiceBuilder);
	
    void setLoggerServiceBuilder(IServiceBuilder<ILoggerService> loggerServiceBuilder);
    
    void setMailServiceBuilder(IServiceBuilder<IMailService> mailServiceBuilder);
    
}
