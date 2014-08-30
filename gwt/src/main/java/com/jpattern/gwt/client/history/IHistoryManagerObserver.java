package com.jpattern.gwt.client.history;

import java.util.List;

/**
 * 
 * @author Francesco Cina
 *
 * 02/ago/2011
 */
public interface IHistoryManagerObserver {

	void onEvent(String eventName);
	
	void onRegisterHistory(String currentToken, List<String> presenterHierarchy) ;
}
