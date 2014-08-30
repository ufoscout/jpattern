package com.jpattern.gwt.client;

import com.jpattern.gwt.client.AApplication;
import com.jpattern.gwt.client.cache.PermanentCache;
import com.jpattern.gwt.client.logger.SysoutLoggerService;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class Application extends AApplication {

	@Override
	public void startSystem() {
		setLoggerService(new SysoutLoggerService());
		getCacheService().registerCache(ITestConstants.PERMANENT_CACHE_NAME, new PermanentCache());
	}

	@Override
	public void stopSystem() {
	}

}
