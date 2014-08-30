/* ----------------------------------------------------------------------------
*     PROJECT : EURES
*
*     PACKAGE : eu.europa.ec.empl.eures.xxx
*        FILE : UserData.java
*
*  CREATED BY : ARHS Developments
*          ON : 6 Oct 2011
*
* MODIFIED BY : ARHS Developments
*          ON : $LastChangedDate
*     VERSION : $LastChangedRevision
*
* ----------------------------------------------------------------------------
* Copyright (c) 2011 European Commission - DG EMPL
* ----------------------------------------------------------------------------
*/
package com.jpattern.gwt.client.session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ARHS Developments - cinafr
 * @version $Revision
 */
public class UserData implements IUserData {

	private final String username;
	private final boolean valid;
	private final List<String> roles;
	
	public UserData (String username, boolean valid) {
		this(username, valid , new ArrayList<String>());
	}

	public UserData (String username, boolean valid, String[] roles) {
		this(username, valid, Arrays.asList(roles));
	}
	
	public UserData (String username, boolean valid, List<String> roles) {
		this.username = username;
		this.valid = valid;
		this.roles = roles;
	}
	
	/**
	 * @return the username
	 */
	@Override
	public String getUsername() {
		return username;
	}
	
	/**
	 * @return the valid
	 */
	@Override
	public boolean isValid() {
		return valid;
	}


	@Override
	public List<String> getRoles() {
		return roles ;
	}
	
}
