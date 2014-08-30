package com.jpattern.gwt.event;

import com.jpattern.gwt.client.Application;
import com.jpattern.gwt.client.BaseTest;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.NullApplicationProvider;
import com.jpattern.gwt.client.command.ACommand;
import com.jpattern.gwt.client.command.ICommandResult;
import com.jpattern.gwt.client.event.AEvent;
import com.jpattern.gwt.client.event.AOnErrorAction;
import com.jpattern.gwt.client.event.EventResult;
import com.jpattern.gwt.client.event.IEvent;
import com.jpattern.gwt.client.event.IEventCallback;
import com.jpattern.gwt.client.event.IEventResult;
import com.jpattern.gwt.client.event.NullEventCallback;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.gwt.client.presenter.NullPresenter;
import com.jpattern.shared.result.ErrorMessage;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class AEvent2Test extends BaseTest {

	private final String ERROR_CODE = "error.code";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testEvent1() throws Exception {
		IApplicationProvider provider = new Application();
		OnErrorAction onErrorAction1 = new OnErrorAction();
		OnErrorAction onErrorAction2 = new OnErrorAction();
		OnErrorAction onErrorActionNo1 = new OnErrorAction();
		OnErrorAction onErrorActionNo2 = new OnErrorAction();
		provider.getManagedErrorService().register(ERROR_CODE + "_NO1", onErrorActionNo1);
		provider.getManagedErrorService().register(ERROR_CODE, onErrorAction1);
		provider.getManagedErrorService().register(ERROR_CODE + "_NO2", onErrorActionNo2);
		provider.getManagedErrorService().register(ERROR_CODE, onErrorAction2);
		
		Event event = new Event(new NullPresenter(new NullApplicationProvider()), provider);
		event.launch(new NullEventCallback<Object>());
		
		assertTrue(onErrorAction1.activated);
		assertFalse(onErrorActionNo1.activated);
		assertTrue(onErrorAction2.activated);
		assertFalse(onErrorActionNo2.activated);
		
	}
	
	class Event extends AEvent<Object> {

		public Event(IPresenter presenter, IApplicationProvider provider) {
			super(presenter, provider);
		}

		@Override
		protected ACommand exec() {
			return new ACommand() {
				@Override
				protected void exec(ICommandResult commandResult) {};
			};
		}

		@Override
		protected IEventResult<Object> afertExec(ICommandResult webResult) {
			webResult.getErrorMessages().add(new ErrorMessage(ERROR_CODE, "trigger error"));
			return new EventResult<Object>(webResult, new Object());
		}
		
	}

	class OnErrorAction extends AOnErrorAction {
		
		boolean activated = false;

		@Override
		protected <T> void execute(int responseCode, IErrorMessage errorMessage, IPresenter presenter, IEvent<T> event, IEventCallback<T> eventCallback) {
			assertEquals(ERROR_CODE, errorMessage.getName());
			activated = true;
		}
		
	}
}
