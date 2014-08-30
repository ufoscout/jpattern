package com.jpattern.gwt.client;

import com.jpattern.gwt.client.bus.ABusService;
import com.jpattern.gwt.client.bus.NullBusService;
import com.jpattern.gwt.client.cache.CacheService;
import com.jpattern.gwt.client.cache.ACacheService;
import com.jpattern.gwt.client.history.AHistoryService;
import com.jpattern.gwt.client.history.NullHistoryService;
import com.jpattern.gwt.client.logger.ALoggerService;
import com.jpattern.gwt.client.logger.NullLoggerService;
import com.jpattern.gwt.client.serializer.ASerializerService;
import com.jpattern.gwt.client.serializer.NullSerializerService;
import com.jpattern.gwt.client.session.ISession;
import com.jpattern.gwt.client.session.Session;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public class NullApplicationProvider implements IApplicationProvider {

	@Override
	public ALoggerService getLoggerService() {
		return new NullLoggerService();
	}

	@Override
	public AService getService(String serviceName) {
		return new NullService();
	}

	@Override
	public AServerCallService getServerCallService() {
		return new NullServerCallService();
	}

	@Override
	public ASerializerService getSerializerService() {
		return new NullSerializerService();
	}

	@Override
	public ACacheService getCacheService() {
		return new CacheService();
	}

	@Override
	public AHistoryService getHistoryService() {
		return new NullHistoryService();
	}

	@Override
	public AManagedErrorService getManagedErrorService() {
		return new NullManagedErrorService();
	}

	@Override
	public ISession getSession() {
		return new Session();
	}

	@Override
	public ABusService getBusService() {
		return new NullBusService();
	}

}
