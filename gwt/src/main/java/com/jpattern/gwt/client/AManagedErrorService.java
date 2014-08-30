package com.jpattern.gwt.client;

import com.jpattern.gwt.client.event.AOnErrorAction;
import com.jpattern.gwt.client.event.IEvent;
import com.jpattern.gwt.client.event.IEventCallback;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.shared.result.IErrorMessage;

/**
 * A register of specific callback action to be triggered on specific error received from the server
 * @author Francesco Cina'
 *
 * 21/set/2011
 */
public abstract class AManagedErrorService extends AService {

	/**
	 * register a new action and the error code that triggers it
	 * @param errorCodetrigger
	 * @param onErrorAction
	 */
	public abstract void register(String errorCodeTrigger, AOnErrorAction onErrorAction);

	public abstract <T> void launch(int responseCode, IErrorMessage errorMessage, IPresenter presenter, IEvent<T> event, IEventCallback<T> eventCallback );
	
}
