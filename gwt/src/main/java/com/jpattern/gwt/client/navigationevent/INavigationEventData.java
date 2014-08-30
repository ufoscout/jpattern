package com.jpattern.gwt.client.navigationevent;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * 
 * @author Francesco Cina
 *
 * 03/ago/2011
 */
public interface INavigationEventData {

	HasWidgets getEventTarget();

	INavigationEventCallback getNavigationEventCallback();

	INavigationEventWrapper getNavigationEventWrapper();

}
