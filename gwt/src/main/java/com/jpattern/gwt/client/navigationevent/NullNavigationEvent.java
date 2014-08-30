package com.jpattern.gwt.client.navigationevent;

import java.util.Map;

import com.jpattern.gwt.client.NullApplicationProvider;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.gwt.client.presenter.NullPresenter;

/**
 * 
 * @author Francesco Cina
 *
 * 01/ago/2011
 */
public class NullNavigationEvent implements INavigationEvent {

	private final String name;

	public NullNavigationEvent() {
		this("");
	}
	
	public NullNavigationEvent(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void notifyNavigationEvent(IPresenter parentPresenter,
			Map<String, String> queryStringValues, String[] childrenEvent,
			boolean registerEvent) {
	}

	@Override
	public IPresenter launch(IPresenter parentPresenter,
			Map<String, String> queryStringValues, String[] childrenEvent) {
		return new NullPresenter(new NullApplicationProvider());
	}

	@Override
	public String[] getAllowedRole() {
		return new String[0];
	}

	@Override
	public boolean isRequireAuthentication() {
		return false;
	}

}
