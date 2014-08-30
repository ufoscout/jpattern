package com.jpattern.gwt.client.event;

import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.NullApplicationProvider;
import com.jpattern.gwt.client.command.ACommand;
import com.jpattern.gwt.client.command.ICommandCallBack;
import com.jpattern.gwt.client.command.ICommandResult;
import com.jpattern.gwt.client.logger.ILogger;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 14 Apr 2011
 */
public abstract class AEvent<T> implements IEvent<T> {
	
	private final IPresenter presenter;
	private IApplicationProvider provider;
	private IEventCallback<T> eventCallback;
	private final ILogger logger;

	/**
	 * Create an event using the presenter's provider.
	 * @param presenter
	 */
	public AEvent(IPresenter presenter) {
		this(presenter, presenter.getProvider());
	}
	
	/**
	 * Create an event with a specific provider
	 * @param presenter
	 * @param provider
	 */
	public AEvent(IPresenter presenter, IApplicationProvider provider) {
		this.presenter = presenter;
		this.provider = provider;
		this.logger = getProvider().getLoggerService().getLogger(this.getClass());
	}

	/* (non-Javadoc)
	 * @see com.jpattern.gwt.event.IEvent#launch()
	 */
	@Override
	public final void launch(IEventCallback<T> eventCallback) {
		this.eventCallback = eventCallback;
		logger.debug("launch", "start execution");
		presenter.onEventStart();
		getProvider().getBusService().notifyEventStart(this);
		ACommand command = exec();
		command.visit(getProvider());
		command.exec( new EventCommandCallback());
	}

	/* (non-Javadoc)
	 * @see com.jpattern.gwt.event.IEvent#callback(com.jpattern.gwt.command.IWebResult)
	 */
	private void callback(ICommandResult commandResult) {
		logger.debug("callback", "call  afertExec...");
		IEventResult<T> eventResult = null;
		try {
			eventResult = afertExec(commandResult);
		} catch (RuntimeException e) {
			eventResult = new EventResult<T>(commandResult, null);
		}
		logger.debug("callback", "start execution (event execution finished)");
		presenter.onEventEnd();
		if ( eventResult.getErrorMessages().isEmpty() ) {
			logger.debug("callback", "calling event callback");
			eventCallback.callback(eventResult);
		}
		else {
			logger.warn("callback", eventResult.getErrorMessages().size() + " errors appeared");
			presenter.onEventError(eventResult.getErrorMessages());
			try {
				logger.debug("callback", "calling event callback");
				eventCallback.callback(eventResult);
			} finally {
				checkManagedErrors(eventResult);
			}
		}
		getProvider().getBusService().notifyEventEnd(this, eventResult);
	}
	
	protected abstract ACommand exec();
	
	protected abstract IEventResult<T> afertExec(ICommandResult webResult);

	protected IApplicationProvider getProvider() {
		if (provider == null) {
			provider = new NullApplicationProvider();
		}
		return provider;
	}
	
	private void checkManagedErrors(IEventResult<?> eventResult) {
		for ( IErrorMessage errorMessage : eventResult.getErrorMessages() ) {
			getProvider().getManagedErrorService().launch( eventResult.getResponseCode(), errorMessage, presenter, this, eventCallback );		
		}
	}
	
	private class EventCommandCallback implements ICommandCallBack {

		@Override
		public void callback(ICommandResult commandResult) {
			AEvent.this.callback(commandResult);			
		}
		
	}
}
