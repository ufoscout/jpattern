package com.jpattern.core;

import com.jpattern.service.log.ILoggerService;
import com.jpattern.service.log.NullLoggerService;
import com.jpattern.service.log.reader.ILoggerReaderService;
import com.jpattern.service.log.reader.LoggerReaderService;
import com.jpattern.service.log.reader.NullQueueMessages;
import com.jpattern.service.mail.IMailService;
import com.jpattern.service.mail.NullMailService;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 */
public class NullProvider implements IProvider {

    public IService getService(String aServiceName) {
        return new NullService();
    }

	public boolean contains(String aServiceName) {
		return false;
	}

	@Override
	public ILoggerService getLoggerService() {
		return new NullLoggerService();
	}

	@Override
	public IMailService getMailService() {
		return new NullMailService();
	}

	@Override
	public ILoggerReaderService getLoggerReaderService() {
		return new LoggerReaderService(new NullQueueMessages());
	}

}
