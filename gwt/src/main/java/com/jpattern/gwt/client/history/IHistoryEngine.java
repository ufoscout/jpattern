package com.jpattern.gwt.client.history;

/**
 * The engine that monitor the history events thrown by the browser
 * @author Francesco Cina
 *
 * 02/ago/2011
 */
public interface IHistoryEngine {

	void init(IHistoryManager historyManager);
	
	void registerEvent(String token);
	
	void updateState();
	
}
