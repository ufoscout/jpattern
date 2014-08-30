package com.jpattern.gwt.client.history;

import java.util.Map;

import com.jpattern.gwt.client.navigationevent.INavigationEvent;
import com.jpattern.gwt.client.presenter.IPresenter;

/**
 * 
 * @author Francesco Cina
 *
 * 27/lug/2011
 */
public interface IHistoryManager {

	void updateState();
	
	void onEvent(String eventName);
	
	void setRootPresenter(IPresenter presenter);
	
	void registerHistory(IPresenter parentPresenter, Map<String, String> queryStringValues, String[] childrenEvent, boolean registerEvent, INavigationEvent navigationEvent);

}
