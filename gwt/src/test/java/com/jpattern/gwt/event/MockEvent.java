package com.jpattern.gwt.event;

import com.jpattern.gwt.client.command.ACommand;
import com.jpattern.gwt.client.command.ICommandResult;
import com.jpattern.gwt.client.event.AEvent;
import com.jpattern.gwt.client.event.EventResult;
import com.jpattern.gwt.client.event.IEventResult;
import com.jpattern.gwt.client.presenter.IPresenter;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class MockEvent extends AEvent<Object> {

	private final ACommand command;

	public MockEvent(IPresenter presenter, ACommand command) {
		super(presenter);
		this.command = command;
	}

	@Override
	protected ACommand exec() {
		return command;
	}

	@Override
	protected IEventResult<Object> afertExec(ICommandResult commandResult) {
		return new EventResult<Object>(commandResult, new Object());
	}

}
