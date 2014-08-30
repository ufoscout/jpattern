package com.jpattern.gwt.client.bus;

import java.util.ArrayList;
import java.util.List;

import com.jpattern.gwt.client.event.IEvent;
import com.jpattern.gwt.client.event.IEventResult;

/**
 * 
 * @author Francesco Cina'
 *
 * Dec 5, 2011
 */
public class NullBusService extends ABusService {

	@Override
	public void addGlobalEventObserver(IGlobalEventObserver statusObserver) {
	}

	@Override
	public void removeGlobalEventObserver(IGlobalEventObserver statusObserver) {
	}

	@Override
	public void notifyGlobalEvent(String status) {
	}

	@Override
	public void addEventObserver(IEventObserver eventBusObserver) {
	}

	@Override
	public void removeEventObserver(IEventObserver eventBusObserver) {
	}

	@Override
	public void addNavigationEventObserver(
			INavigationEventObserver navigationEventObserver) {
	}

	@Override
	public void removeNavigationEventObserver(
			INavigationEventObserver navigationEventObserver) {
	}

	@Override
	public <T> void notifyEventStart(IEvent<T> event) {
	}

	@Override
	public void notifyNavigationEvent(String navigationEventName) {
	}

	@Override
	public <T> void notifyEventEnd(IEvent<T> event, IEventResult<T> eventResult) {
	}

	@Override
	public List<INavigationEventObserver> getNavigationEventObservers() {
		return new ArrayList<INavigationEventObserver>();
	}

	@Override
	public List<IEventObserver> getEventObservers() {
		return new ArrayList<IEventObserver>();
	}

	@Override
	public List<IGlobalEventObserver> getStatusObservers() {
		return new ArrayList<IGlobalEventObserver>();
	}

}
