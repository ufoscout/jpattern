package com.jpattern.gwt.client.session;

import java.util.List;

/**
 * 
 * @author cinafr
 *
 */
public class SecurityContext implements ISecurityContext {

	private IUserData userSession = new UserData("", false);

	@Override
	public boolean isUserInRole(String roleName) {
		if (roleName==null) {
			return false;
		}
		return getUserData().getRoles().contains(roleName);
	}
	
	@Override
	public boolean isUserInRole(List<String> roles) {
		if (roles==null) {
			return false;
		}
		return isUserInRole(roles.toArray(new String[roles.size()]));
	}
	
	@Override
	public boolean isUserInRole(String[] roles) {
		if (roles==null) {
			return false;
		}
		boolean userAllowed = true;
		IUserData userSession = getUserData();
		if ( roles.length>0 ) {
			userAllowed = false;
			for ( String roleAllowed : roles) {
				if (userSession.getRoles().contains(roleAllowed)) {
					userAllowed = true;
					break;
				}
			}
		}
		return userAllowed;
	}

	@Override
	public boolean isUserValid() {
		return getUserData().isValid();
	}

	@Override
	public IUserData getUserData() {
		return userSession;
	}
	
	@Override
	public final boolean isAuthorized(boolean requiredAuthentication, String[] requiredRoles) {
		if (requiredAuthentication && !isUserValid()) {
			return false;
		}
		return isUserInRole(requiredRoles);
	}

	public void setUserData(IUserData userSession) {
		if (userSession!=null) {
			this.userSession = userSession;
		}
	}

}
