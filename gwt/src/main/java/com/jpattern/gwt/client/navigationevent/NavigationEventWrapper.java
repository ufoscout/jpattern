package com.jpattern.gwt.client.navigationevent;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2011
 */
public class NavigationEventWrapper extends ANavigationEventWrapper {

	private final INavigationEvent navigationEvent;

	public NavigationEventWrapper(INavigationEvent navigationEvent) {
		super(navigationEvent.getName(), navigationEvent.isRequireAuthentication() , navigationEvent.getAllowedRole());
		this.navigationEvent = navigationEvent;
	}

	@Override
	protected void executeCallback(INavigationEventWrapperCallback callback) {
		callback.exec(navigationEvent);
	}

}
