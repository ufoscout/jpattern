package com.jpattern.gwt.client.presenter;

import com.jpattern.gwt.client.navigationevent.INavigationEvent;
import com.jpattern.gwt.client.navigationevent.NullNavigationEvent;

/**
 * 
 * @author Francesco Cina'
 *
 * Nov 28, 2011
 */
public class PresenterDefinition implements IPresenterDefinition {

	private final String name;
	private final boolean requireAuthentication;
	private final String[] allowedRoles;
	private final INavigationEvent navigationEvent;

	public PresenterDefinition() {
		this("", false, new String[0]);
	}
	
	public PresenterDefinition (String name, boolean requireAuthentication, String[] allowedRoles) {
		this(name, requireAuthentication, allowedRoles, new NullNavigationEvent());
	}
	
	public PresenterDefinition (String name, boolean requireAuthentication, String[] allowedRoles, INavigationEvent navigationEvent) {
		this.name = name;
		this.requireAuthentication = requireAuthentication;
		this.allowedRoles = allowedRoles;
		this.navigationEvent = navigationEvent;
		
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean requireAuthentication() {
		return requireAuthentication;
	}

	@Override
	public String[] getAllowedRoles() {
		return allowedRoles;
	}

	@Override
	public INavigationEvent getNavigationEvent() {
		return navigationEvent;
	}

}
