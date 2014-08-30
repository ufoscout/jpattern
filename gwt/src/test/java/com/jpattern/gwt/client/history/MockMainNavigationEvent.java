package com.jpattern.gwt.client.history;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.user.client.ui.HasWidgets;
import com.jpattern.gwt.client.navigationevent.ANavigationEvent;
import com.jpattern.gwt.client.navigationevent.INavigationEvent;
import com.jpattern.gwt.client.navigationevent.INavigationEventCallback;
import com.jpattern.gwt.client.navigationevent.NullNavigationEventCallback;
import com.jpattern.gwt.client.presenter.IPresenter;

public class MockMainNavigationEvent extends ANavigationEvent {

	private Map<String, Map<String, String>> initialEvent = new HashMap<String, Map<String, String>>();
	private Map<INavigationEvent, HasWidgets > navigationEvents = new HashMap<INavigationEvent, HasWidgets>();
	
	public MockMainNavigationEvent(String eventName) {
		super(eventName);
	}

	@Override
	public IPresenter exec(Map<String, String> queryStringValues) {
		System.out.println(getClass() + " exec called");
		IPresenter presenter = new MockPresenter(getPresenterDefinition());
//		presenter.setProvider(getProvider());
		System.out.println("Create new presenter");
		for (Entry<INavigationEvent, HasWidgets> entry : navigationEvents.entrySet()) {
			presenter.registerNavigationEvent(entry.getKey(), entry.getValue(), new NullNavigationEventCallback());
			System.out.println("Register event: " + entry.getKey().getName());
		}
		for (Entry<String, Map<String, String>> event : initialEvent.entrySet()) {
			presenter.launchNavigationEvent(event.getKey(), false ,event.getValue());
			System.out.println("Launch event: " + event);
		}
		return presenter;
	}

	public void registerNavigationEvent(INavigationEvent navigationEvent, HasWidgets eventTarget, INavigationEventCallback navigationEventCallback) {
		navigationEvents.put(navigationEvent, eventTarget);
		
	}

	public void addInitialNavigationEvent(String string) {
		addInitialNavigationEvent(string, new HashMap<String, String>());
	}
	
	public void addInitialNavigationEvent(String string,  Map<String, String> queryString) {
		initialEvent.put(string, queryString);
	}
	
}
