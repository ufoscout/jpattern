package com.jpattern.gwt.client.session;

import java.util.List;

public interface ISecurityContext {

	/**
	 * Return current user in session
	 * @return
	 */
	public abstract IUserData getUserData();
	
	/**
	 * Return true if the current user has the roleName role
	 * @param roleName
	 */
	public abstract boolean isUserInRole(String roleName);

	/**
	 * Return true if:
	 * - the current user has at least one the roles in the 'roles' list.
	 * - the 'roles' list is empty 
	 * @param roles
	 */
	public abstract boolean isUserInRole(String[] roles);
	
	/**
	 * Return true if:
	 * - the current user has at least one the roles in the 'roles' list.
	 * - the 'roles' list is empty 
	 * @param roles
	 */
	public abstract boolean isUserInRole(List<String> roles);
	
	/**
	 * Return true if:
	 * - the current user has at least one the roles in the 'roles' list.
	 * - the 'roles' list is empty 
	 * @param roles
	 */
	public abstract boolean isUserValid();
	
	/**
	 * Return true if the current user matches the requirements
	 * @param requiredAuthentication
	 * @param requiredRoles
	 * @return
	 */
	public abstract boolean isAuthorized(boolean requiredAuthentication, String[] requiredRoles);

}
