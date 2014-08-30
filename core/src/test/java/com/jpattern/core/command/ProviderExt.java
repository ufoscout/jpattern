package com.jpattern.core.command;

import com.jpattern.core.IProvider;
import com.jpattern.service.log.ILoggerService;
import com.jpattern.service.log.reader.ILoggerReaderService;
import com.jpattern.service.mail.IMailService;

public class ProviderExt implements IProvider {

	private final IProvider provider;

	ProviderExt(IProvider provider) {
		this.provider = provider;
	}

	@Override
	public ILoggerService getLoggerService() {
		return provider.getLoggerService(); 
	}

	@Override
	public IMailService getMailService() {
		return provider.getMailService();
	}

	public Object getNull() {
		return null;
	}

	@Override
	public ILoggerReaderService getLoggerReaderService() {
		return provider.getLoggerReaderService();
	}

}
