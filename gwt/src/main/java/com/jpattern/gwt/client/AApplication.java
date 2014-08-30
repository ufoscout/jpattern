package com.jpattern.gwt.client;

import java.util.HashMap;
import java.util.Map;

import com.jpattern.gwt.client.bus.ABusService;
import com.jpattern.gwt.client.bus.BusService;
import com.jpattern.gwt.client.cache.CacheService;
import com.jpattern.gwt.client.cache.ACacheService;
import com.jpattern.gwt.client.history.DirectHistoryEngine;
import com.jpattern.gwt.client.history.HistoryService;
import com.jpattern.gwt.client.history.AHistoryService;
import com.jpattern.gwt.client.history.NullHistoryManagerObserver;
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
public abstract class AApplication implements IApplicationProvider, IApplicationSystem {

	private Map<String, AService> serviceMap = new HashMap<String, AService>();
	
	private ASerializerService serializerService;
	private ALoggerService loggerService;
	private AServerCallService serverCallService;
	private ACacheService cacheService;
	private AHistoryService historyService;
	private AManagedErrorService managedErrorService;
	private Session session = new Session();
	private ABusService busService;

	public AApplication() {
		setSerializerService( new NullSerializerService() );
		setLoggerService( new NullLoggerService() );
		setServerCallService( new RestServerCallService() );
		setCacheService( new CacheService() );
		setHistoryService( new HistoryService(new DirectHistoryEngine(new NullLoggerService()), new NullHistoryManagerObserver(), new NullLoggerService()) );
		setManagedErrorService( new ManagedErrorService(this) );
		setStatusService( new BusService(this) );
	}
	
	@Override
	public final ALoggerService getLoggerService() {
		return loggerService;
	}
	
	@Override
	public final void setLoggerService(ALoggerService loggerService) {
		if (loggerService!=null) {
			this.loggerService = loggerService;
			this.loggerService.setProvider(this);
		}
	}
	
	@Override
	public final AServerCallService getServerCallService() {
		return serverCallService;
	}
	
	@Override
	public final void setServerCallService(AServerCallService serverCallService) {
		if (serverCallService!=null) {
			this.serverCallService = serverCallService;
			this.serverCallService.setProvider(this);
		}
	}
	
	@Override
	public final AService getService(String serviceName) {
		if (serviceMap.containsKey(serviceName)) {
			return serviceMap.get(serviceName);
		}
		return new NullService();
	}
	
	@Override
	public final void addService(String serviceName, AService service){
		service.setProvider(this);
		serviceMap.put(serviceName, service);
	}

	@Override
	public final ASerializerService getSerializerService() {
		return serializerService;
	}

	@Override
	public final void setSerializerService(ASerializerService serializerService) {
		this.serializerService = serializerService;
		this.serializerService.setProvider(this);
	}
	
	@Override
	public final ACacheService getCacheService() {
		return cacheService ;
	}
	
	@Override
	public final AHistoryService getHistoryService() {
		return historyService;
	}

	@Override
	public final void setHistoryService(AHistoryService historyService) {
		this.historyService = historyService;
		this.historyService.setProvider(this);
	}
	
	@Override
	public final AManagedErrorService getManagedErrorService() {
		return managedErrorService;
	}
	
	@Override
	public final ISession getSession() {
		return session;
	}
	
	@Override
	public final void setCacheService(ACacheService cacheService) {
		if (this.cacheService != null) {
			session.removeObserver(this.cacheService);
		}
		session.addObserver(cacheService);
		this.cacheService = cacheService;
		this.cacheService.setProvider(this);
	}

	@Override
	public final void setManagedErrorService(AManagedErrorService managedErrorService) {
		this.managedErrorService = managedErrorService;
		this.managedErrorService.setProvider(this);
	}

	@Override
	public final ABusService getBusService() {
		return busService;
	}
	
	@Override
	public final void setStatusService(ABusService busService) {
		this.busService = busService;
	}
	
}
