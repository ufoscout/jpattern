package com.jpattern.gwt.client;

import com.jpattern.gwt.client.event.AOnErrorAction;
import com.jpattern.gwt.client.event.IEvent;
import com.jpattern.gwt.client.event.IEventCallback;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 21/set/2011
 */
public class NullManagedErrorService extends AManagedErrorService {

	@Override
	public void register(String errorCodetrigger, AOnErrorAction onErrorAction) {
	}

	@Override
	public <T> void launch(int responseCode, IErrorMessage errorMessage, IPresenter presenter, IEvent<T> event, IEventCallback<T> eventCallback) {
	}

}
