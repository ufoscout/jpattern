package com.jpattern.gwt.event;

import java.util.ArrayList;
import java.util.List;

import com.jpattern.gwt.client.BaseTest;
import com.jpattern.gwt.client.command.CommandChainSynch;
import com.jpattern.gwt.client.event.IEvent;
import com.jpattern.gwt.client.event.IEventCallback;
import com.jpattern.gwt.client.event.IEventResult;
import com.jpattern.gwt.command.MockCommand;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class AEventTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testEvent1() throws Exception {
		CommandChainSynch commandChain = new CommandChainSynch();
		MockCommand myCommand1 = new MockCommand(false, false);
		MockCommand myCommand2 = new MockCommand(false, false);
		MockCommand myCommand3 = new MockCommand(false, false);
		commandChain.addCommand(myCommand1);
		commandChain.addCommand(myCommand2);
		commandChain.addCommand(myCommand3);
		MockView view = new MockView();
		MockPresenter presenter = new MockPresenter(view);
		
		IEvent<Object> event = new MockEvent(presenter, commandChain);
		MyEventCallback eventCallback = new MyEventCallback();
		event.launch(eventCallback);
		
		assertTrue( eventCallback.getErrorMessages().isEmpty() );
		assertTrue( myCommand1.isExecuted() );
		assertTrue( myCommand2.isExecuted() );
		assertTrue( myCommand3.isExecuted() );
		assertTrue( view.getNotificationArea().getErrorMessages().isEmpty() );
		assertTrue( ((MockErrorArea) view.getNotificationArea()).operationStarted );
		assertTrue( ((MockErrorArea) view.getNotificationArea()).operationFinished );
	}
	
	
	public void testEvent2() throws Exception {
		CommandChainSynch commandChain = new CommandChainSynch();
		MockCommand myCommand1 = new MockCommand(false, false);
		MockCommand myCommand2 = new MockCommand(false, true);
		MockCommand myCommand3 = new MockCommand(false, false);
		commandChain.addCommand(myCommand1);
		commandChain.addCommand(myCommand2);
		commandChain.addCommand(myCommand3);
		MockView view = new MockView();
		MockPresenter presenter = new MockPresenter(view);
		
		IEvent<Object> event = new MockEvent(presenter, commandChain);
		MyEventCallback eventCallback = new MyEventCallback();
		event.launch(eventCallback);
		
		assertTrue( eventCallback.getErrorMessages().size()>0 );
		assertTrue( myCommand1.isExecuted() );
		assertTrue( myCommand2.isExecuted() );
		assertFalse( myCommand3.isExecuted() );
		assertEquals( eventCallback.getErrorMessages().size() , view.getNotificationArea().getErrorMessages().size() );
	}
	
	class MyEventCallback implements IEventCallback<Object> {

		private Object result;
		private List<IErrorMessage> errorMessages = new ArrayList<IErrorMessage>();

		public Object getResult() {
			return result;
		}

		public List<IErrorMessage> getErrorMessages() {
			return errorMessages;
		}

		@Override
		public void callback(IEventResult<Object> eventResult) {
			this.result = eventResult.getReturnedObject();
			errorMessages.addAll( eventResult.getErrorMessages());			
		}
		
	}
}
