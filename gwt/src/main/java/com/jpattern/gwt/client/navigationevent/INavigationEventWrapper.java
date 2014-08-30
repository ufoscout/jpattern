package com.jpattern.gwt.client.navigationevent;

import java.util.Map;

import com.jpattern.gwt.client.presenter.IPresenter;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2011
 */
public interface INavigationEventWrapper extends IBaseNavigationEvent {

	void notifyNavigationEvent(IPresenter parentPresenter,
			Map<String, String> queryStringValues, String[] childrenEvent, boolean registerEvent);

}