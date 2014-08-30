package com.jpattern.gwt.history;

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
	
	private MockPresenter presenter = new MockPresenter(getPresenterDefinition());

	MockNavigationEvent(String eventName) {
		super(eventName);
	}

	@Override
	public IPresenter exec(Map<String, String> queryString) {
		return presenter;
	}
	
	MockPresenter getPresenter() {
		return presenter;
	}

}
