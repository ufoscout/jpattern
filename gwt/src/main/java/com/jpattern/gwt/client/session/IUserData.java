/* ----------------------------------------------------------------------------
*     PROJECT : EURES
*
*     PACKAGE : eu.europa.ec.empl.eures.xxx
*        FILE : IUserData.java
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

import java.util.List;

/**
 *
 * @author ARHS Developments - cinafr
 * @version $Revision
 */
public interface IUserData {

	List<String> getRoles();
	
	boolean isValid();
	
	String getUsername();

}
