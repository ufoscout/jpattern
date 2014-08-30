package com.jpattern.gwt.client.navigationevent;

import java.util.Map;

import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.gwt.client.presenter.IPresenterDefinition;
import com.jpattern.gwt.client.presenter.PresenterDefinition;

/**
 * 
 * @author Francesco Cina
 *
 * 01/ago/2011
 */
public abstract class ANavigationEvent extends ABaseNavigationEvent implements INavigationEvent {

	private IPresenter presenter;

	/**
	 * 
	 * @param eventName the unique event name
	 */
	public ANavigationEvent(String eventName) {
		this(eventName, false, new String[0]);
	}
	
	/**
	 * 
	 * @param eventName
	 * @param requireAuthentication whether the user have to be logged-in to access the presenter
	 * @param allowedRoles = The list of roles allowed to access this presenter.
	 * If a user doesn't belong to the roles list, the presenter will not be executed
	 * An empty array means no restriction to the access (default behavior).
	 */
	public ANavigationEvent(String eventName, boolean requireAuthentication, String[] allowedRoles) {
		super(eventName, requireAuthentication, allowedRoles);
	}
	
	@Override
	public final void notifyNavigationEvent(IPresenter parentPresenter, Map<String, String> queryStringValues, String[] childrenEvent, boolean registerEvent) {
		parentPresenter.getProvider().getHistoryService().getHistoryManager().registerHistory(parentPresenter, queryStringValues, childrenEvent, registerEvent, this);
	}
	
	@Override
	public final IPresenter launch(IPresenter parentPresenter, Map<String, String> queryStringValues, String[] childrenEvent) {
		IApplicationProvider provider = parentPresenter.getProvider();
		provider.getBusService().notifyNavigationEvent(getName());
		IPresenter presenter = getPresenter(queryStringValues, provider);
		presenter.launchNavigationEvent(childrenEvent, false, queryStringValues);
		initPresenter(parentPresenter, presenter);
		return presenter;
	}
	
	private void initPresenter(IPresenter parentPresenter, IPresenter presenter) {
		presenter.setParent(parentPresenter);
		presenter.initPresenter();
		parentPresenter.getView().onNavigationEvent(getName());
	}
	
	private IPresenter getPresenter(Map<String, String> queryStringValues, IApplicationProvider provider) {
		presenter = exec(queryStringValues);
		presenter.setProvider(provider);
		return presenter;
	}

	/**
	 * Return the newly created presenter
	 * @return
	 */
	protected abstract IPresenter exec(Map<String, String> queryStringValues); 
	
	/**
	 * Build a presenter definition based on the allowedRoles and the requireAuthentication fields.
	 * The name of the presenter is set as the same of the current ANavigationEvent
	 * @return
	 */
	protected IPresenterDefinition getPresenterDefinition() {
		return new PresenterDefinition(getName(), isRequireAuthentication(), getAllowedRole(), this);
	}
}
