package com.jpattern.gwt.client.navigationevent;

import java.util.Map;

import com.jpattern.gwt.client.presenter.IPresenter;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2011
 */
public abstract class ANavigationEventWrapper extends ABaseNavigationEvent implements INavigationEventWrapper {

	/**
	 * 
	 * @param eventName the unique event name
	 */
	public ANavigationEventWrapper(String eventName) {
		this(eventName, false, new String[0]);
	}
	
	/**
	 * 
	 * @param eventName
	 * @param requireAuthentication whether the user have to be logged-in to access the presenter
	 * @param allowedRoles = The list of roles allowed to access this presenter.
	 * If a user doesn't belong to the roles list, the presenter will not be executed
	 * An empty array means no restriction to the access (default behaviour).
	 */
	public ANavigationEventWrapper(String eventName, boolean requireAuthentication, String[] allowedRoles) {
		super(eventName, requireAuthentication, allowedRoles);
	}
	
	
	@Override
	public final void notifyNavigationEvent(final IPresenter parentPresenter, final Map<String, String> queryStringValues, final String[] childrenEvent, final boolean registerEvent) {
		INavigationEventWrapperCallback callback = new INavigationEventWrapperCallback() {
			@Override
			public void exec(INavigationEvent navigationEvent) {
				navigationEvent.notifyNavigationEvent(parentPresenter, queryStringValues, childrenEvent, registerEvent);
			}
		};
		executeCallback(callback);
	}

	protected abstract void executeCallback(INavigationEventWrapperCallback callback);

}