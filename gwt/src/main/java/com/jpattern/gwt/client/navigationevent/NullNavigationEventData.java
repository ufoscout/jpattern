package com.jpattern.gwt.client.navigationevent;

import com.google.gwt.user.client.ui.HasWidgets;
import com.jpattern.gwt.client.view.NullHasWidgets;

/**
 * 
 * @author Francesco Cina
 *
 * 03/ago/2011
 */
public class NullNavigationEventData implements INavigationEventData {

	private final INavigationEventWrapper navigationEvent = new NullNavigationEventWrapper();
	private final INavigationEventCallback navigationEventCallback = new NullNavigationEventCallback();
	private final HasWidgets eventTarget = new NullHasWidgets();

	@Override
	public INavigationEventWrapper getNavigationEventWrapper() {
		return navigationEvent;
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
