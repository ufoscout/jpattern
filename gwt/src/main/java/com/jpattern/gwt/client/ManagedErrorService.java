package com.jpattern.gwt.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpattern.gwt.client.event.AOnErrorAction;
import com.jpattern.gwt.client.event.IEvent;
import com.jpattern.gwt.client.event.IEventCallback;
import com.jpattern.gwt.client.logger.ILogger;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 21/set/2011
 */
public class ManagedErrorService extends AManagedErrorService {

	private final Map<String, List<AOnErrorAction>> errorActionMap = new HashMap<String, List<AOnErrorAction>>();
	private final IApplicationProvider provider;
	private final ILogger logger;
	
	public ManagedErrorService(IApplicationProvider provider) {
		this.provider = provider;
		this.logger = provider.getLoggerService().getLogger(getClass());
	}
	
	@Override
	public void register(String errorCodeTrigger, AOnErrorAction onErrorAction) {
		logger.debug("register", "Register errorCodeTrigger: [" + errorCodeTrigger + "]");
		onErrorAction.setProvider(provider);
		if (!errorActionMap.containsKey(errorCodeTrigger)) {
			errorActionMap.put(errorCodeTrigger, new ArrayList<AOnErrorAction>());
		}
		errorActionMap.get(errorCodeTrigger).add(onErrorAction);
	}

	@Override
	public <T> void launch(int responseCode, IErrorMessage errorMessage, IPresenter presenter, IEvent<T> event, IEventCallback<T> eventCallback ) {
		String errorCode = errorMessage.getName();
		logger.debug("launch", "Check errorCodeTrigger: for error [" + errorCode + "]");
		if (errorActionMap.containsKey(errorCode)) {
			logger.debug("launch", "Launch errorCodeTrigger: [" + errorCode + "]");
			for ( AOnErrorAction onErrorAction : errorActionMap.get(errorCode) ) {
				onErrorAction.launch(responseCode, errorMessage, presenter, event, eventCallback);
			}
		}
	}

}
