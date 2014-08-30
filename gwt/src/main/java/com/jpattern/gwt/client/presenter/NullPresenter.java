package com.jpattern.gwt.client.presenter;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.ui.HasWidgets;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.navigationevent.INavigationEvent;
import com.jpattern.gwt.client.navigationevent.INavigationEventCallback;
import com.jpattern.gwt.client.navigationevent.INavigationEventData;
import com.jpattern.gwt.client.navigationevent.INavigationEventWrapper;
import com.jpattern.gwt.client.navigationevent.NullNavigationEvent;
import com.jpattern.gwt.client.navigationevent.NullNavigationEventData;
import com.jpattern.gwt.client.view.IView;
import com.jpattern.gwt.client.view.NullView;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class NullPresenter implements IPresenter {

	private IApplicationProvider provider;

	public NullPresenter(IApplicationProvider provider) {
		this.provider = provider;
	}
	
	@Override
	public void onEventError(List<IErrorMessage> errorMessages) {
	}

	@Override
	public void onEventStart() {
	}
	
	@Override
	public void onEventEnd() {
	}
	
	@Override
	public void initPresenter() {
	}

	@Override
	public void hierarchy(List<String> hierarchyResult) {
	}

	@Override
	public IView getView() {
		return new NullView();
	}

	@Override
	public void registerNavigationEvent(INavigationEvent navigationEvent, HasWidgets eventTarget, INavigationEventCallback navigationEventCallback) {
		
	}

	@Override
	public INavigationEventData getNavigationEventData(String navigationEventName) {
		return new NullNavigationEventData();
	}

	@Override
	public void launchNavigationEvent(String navigationEventName, boolean registerHistory) {
	}

	@Override
	public INavigationEvent getNavigationEvent() {
		return new NullNavigationEvent();
	}

	@Override
	public void setParent(IPresenter parentPresenter) {
	}

	@Override
	public boolean isInitialized() {
		return false;
	}


	@Override
	public String getName() {
		return "";
	}

	@Override
	public void launchNavigationEvent(String navigationEventName, boolean registerHistory, Map<String, String> queryStringValues) {
	}

	@Override
	public void registerNavigationEvent(
			INavigationEventWrapper navigationEventWrapper,
			HasWidgets eventTarget,
			INavigationEventCallback navigationEventCallback) {
	}

	@Override
	public void onUnload() {
	}

	@Override
	public void launchNavigationEvent(String[] navigationEventsName,
			boolean registerHistory, Map<String, String> queryStringValues) {
	}

	@Override
	public void launchNavigationEvent(String[] navigationEventsName,
			boolean registerHistory) {
	}

	@Override
	public void setProvider(IApplicationProvider provider) {
		this.provider = provider;
	}

	@Override
	public void launchNavigationEvent(String[] navigationEventsName,
			boolean registerHistory, Map<String, String> queryStringValues,
			boolean onlyIfTargetNotUsed) {
	}

	@Override
	public String[] getAllowedRole() {
		return new String[0];
	}

	@Override
	public boolean isUserAuthorized() {
		return false;
	}

	@Override
	public IApplicationProvider getProvider() {
		return provider;
	}

}
