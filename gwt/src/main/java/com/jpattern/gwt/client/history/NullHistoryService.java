package com.jpattern.gwt.client.history;

/**
 * 
 * @author Francesco Cina
 *
 * 27/lug/2011
 */
public class NullHistoryService extends AHistoryService {

	@Override
	public IHistoryManager getHistoryManager() {
		return new NullHistoryManager();
	}

}
