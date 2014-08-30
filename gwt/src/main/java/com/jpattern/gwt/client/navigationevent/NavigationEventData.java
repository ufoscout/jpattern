package com.jpattern.gwt.client.navigationevent;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/ago/2011
 */
public class NavigationEventData implements INavigationEventData {

	private final INavigationEventWrapper navigationEventWrapper;
	private final INavigationEventCallback navigationEventCallback;
	private final HasWidgets eventTarget;

	public NavigationEventData(INavigationEventWrapper navigationEventWrapper, HasWidgets eventTarget, INavigationEventCallback navigationEventCallback) {
		this.navigationEventWrapper = navigationEventWrapper;
		this.eventTarget = eventTarget;
		this.navigationEventCallback = navigationEventCallback;
	}

	@Override
	public INavigationEventWrapper getNavigationEventWrapper() {
		return navigationEventWrapper;
	}

	@Override
	public INavigationEventCallback getNavigationEventCallback() {
		return navigationEventCallback;
	}

	@Override
	public HasWidgets getEventTarget() {
		return eventTarget;
	}

}
