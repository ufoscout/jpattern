package com.jpattern.gwt.client.history;

import com.jpattern.gwt.client.logger.ALoggerService;

/**
 * A service to store a fixed number of history steps
 * 
 * @author Francesco Cina
 *
 * 27/lug/2011
 */
public class HistoryService extends AHistoryService {

	private final IHistoryManager historyManager;
	
	public HistoryService(ALoggerService loggerService) {
		this(new GWTHistoryEngine(), new NullHistoryManagerObserver() , loggerService);
	}
	
	public HistoryService(IHistoryEngine historyEngine, IHistoryManagerObserver historyManagerObserver, ALoggerService loggerService) {
		historyManager = new HistoryManager(historyEngine, historyManagerObserver, loggerService);
	}
	
	@Override
	public IHistoryManager getHistoryManager() {
		return historyManager;
	}

}
