package com.jpattern.gwt.client.navigationevent;

/**
 * 
 * @author Francesco Cina'
 *
 * Nov 28, 2011
 */
public abstract class ABaseNavigationEvent implements IBaseNavigationEvent {

	private final String name;
	private final String[] allowedRoles;
	private final boolean requireAuthentication;

	public ABaseNavigationEvent(String name, boolean requireAuthentication, String[] allowedRoles) {
		this.name = name;
		this.requireAuthentication = requireAuthentication;
		this.allowedRoles = allowedRoles;
	}
	
	@Override
	public final String getName() {
		return name;
	}
	
	@Override
	public final String[] getAllowedRole() {
		return allowedRoles;
	}
	
	@Override
	public boolean isRequireAuthentication() {
		return requireAuthentication;
	}
	
}
