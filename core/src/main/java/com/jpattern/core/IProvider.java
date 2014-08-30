package com.jpattern.core;

import com.jpattern.service.log.ILoggerService;
import com.jpattern.service.log.reader.ILoggerReaderService;
import com.jpattern.service.mail.IMailService;

/**
 * 
 * @author Francesco Cina'
 *
 * 07/apr/2010
 */
public interface IProvider {
	
	ILoggerReaderService getLoggerReaderService();
	
    ILoggerService getLoggerService();
    
    IMailService getMailService();
    
}
