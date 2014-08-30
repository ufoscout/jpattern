package com.jpattern.gwt.client;

import com.jpattern.gwt.client.bus.ABusService;
import com.jpattern.gwt.client.cache.ACacheService;
import com.jpattern.gwt.client.history.AHistoryService;
import com.jpattern.gwt.client.logger.ALoggerService;
import com.jpattern.gwt.client.serializer.ASerializerService;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public interface IApplicationSystem {

	void startSystem();
	
	void stopSystem();
	
	void addService(String serviceName, AService service);
	
	void setLoggerService(ALoggerService loggerService);

	void setServerCallService(AServerCallService serverCallService);

	void setSerializerService(ASerializerService serializerService);
	
	void setHistoryService(AHistoryService historyService);
	
	void setCacheService(ACacheService cacheService);
	
	void setManagedErrorService(AManagedErrorService managedErrorService);
	
	void setStatusService(ABusService busService);
	
}
