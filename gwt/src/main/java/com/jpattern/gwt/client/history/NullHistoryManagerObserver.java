package com.jpattern.gwt.client.history;

import java.util.List;

/**
 * 
 * @author Francesco Cina
 *
 * 02/ago/2011
 */
public class NullHistoryManagerObserver implements IHistoryManagerObserver {

	@Override
	public void onRegisterHistory(String currentToken, List<String> presenterHierarchy) {
	}

	@Override
	public void onEvent(String eventName) {
	}

}
