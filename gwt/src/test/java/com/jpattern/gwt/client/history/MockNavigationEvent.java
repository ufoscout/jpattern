package com.jpattern.gwt.client.history;

import java.util.Map;

import com.jpattern.gwt.client.navigationevent.ANavigationEvent;
import com.jpattern.gwt.client.presenter.IPresenter;

/**
 * 
 * @author Francesco Cina
 *
 * 02/ago/2011
 */
public class MockNavigationEvent extends ANavigationEvent {
	
	private MockNavigationPresenter presenter = new MockNavigationPresenter(getPresenterDefinition());

	MockNavigationEvent(String eventName) {
		super(eventName);
	}

	@Override
	public IPresenter exec(Map<String, String> queryString) {
		return presenter;
	}
	
	MockNavigationPresenter getPresenter() {
		return presenter;
	}

}
