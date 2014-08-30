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
public class NullHistoryManager implements IHistoryManager {

	@Override
	public void onEvent(String eventName) {
	}

	@Override
	public void updateState() {
	}

	@Override
	public void registerHistory(IPresenter parentPresenter, Map<String, String> queryStringValues, String[] childrenEvent, boolean registerEvent, INavigationEvent navigationEvent) {
	}

	@Override
	public void setRootPresenter(IPresenter presenter) {
	}

}
