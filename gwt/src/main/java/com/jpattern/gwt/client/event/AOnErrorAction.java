package com.jpattern.gwt.client.event;

import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.shared.result.IErrorMessage;

/**
 * An action that can be registered at application start and triggered for a specific error code
 * returned by an event call
 * 
 * @author Francesco Cina'
 *
 * 21/set/2011
 */
public abstract class AOnErrorAction {

	private IApplicationProvider provider;

	protected abstract <T> void execute(int responseCode, IErrorMessage errorMessage, IPresenter presenter, IEvent<T> event, IEventCallback<T> eventCallback );
	
	public <T> void launch(int responseCode, IErrorMessage errorMessage, IPresenter presenter, IEvent<T> event, IEventCallback<T> eventCallback ) {
		presenter.getView().getNotificationArea().clear();
		execute(responseCode, errorMessage, presenter, event, eventCallback);
	}
	
	protected IApplicationProvider getProvider() {
		return provider;
	}

	public void setProvider(IApplicationProvider provider) {
		this.provider = provider;
	}
	
}
