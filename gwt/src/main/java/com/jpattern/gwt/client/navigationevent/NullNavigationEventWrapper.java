package com.jpattern.gwt.client.navigationevent;

import java.util.Map;

import com.jpattern.gwt.client.presenter.IPresenter;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2011
 */
public class NullNavigationEventWrapper implements INavigationEventWrapper {

	@Override
	public void notifyNavigationEvent(IPresenter parentPresenter,
			Map<String, String> queryStringValues, String[] childrenEvent, boolean registerEvent) {
	}

	@Override
	public String getName() {
		return "";
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
