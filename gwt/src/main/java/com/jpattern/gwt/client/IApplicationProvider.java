package com.jpattern.gwt.client;

import com.jpattern.gwt.client.bus.ABusService;
import com.jpattern.gwt.client.cache.ACacheService;
import com.jpattern.gwt.client.history.AHistoryService;
import com.jpattern.gwt.client.logger.ALoggerService;
import com.jpattern.gwt.client.serializer.ASerializerService;
import com.jpattern.gwt.client.session.ISession;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public interface IApplicationProvider {
	
	AService getService(String serviceName);

	ALoggerService getLoggerService();
	
	AServerCallService getServerCallService();
	
	ASerializerService getSerializerService();
	
	ACacheService getCacheService();
	
	AHistoryService getHistoryService();

	AManagedErrorService getManagedErrorService();
	
	ABusService getBusService();
	
	ISession getSession();

}
