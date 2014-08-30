/* ----------------------------------------------------------------------------
*     PROJECT : EURES
*
*     PACKAGE : eu.europa.ec.empl.eures.pdt.client.presenter
*        FILE : APreInitPhasePresenter.java
*
*  CREATED BY : ARHS Developments
*          ON : Dec 13, 2011
*
* MODIFIED BY : ARHS Developments
*          ON : $LastChangedDate
*     VERSION : $LastChangedRevision
*
* ----------------------------------------------------------------------------
* Copyright (c) 2011 European Commission - DG EMPL
* ----------------------------------------------------------------------------
*/
package com.jpattern.gwt.client.presenter;

import java.util.Map;

import com.jpattern.gwt.client.view.IShowViewStrategy;
import com.jpattern.gwt.client.view.IView;

/**
 * <class_description> This is a particular implementation of the APresenter that permits to do special actions before
 * the initialisation phase. This class is to use with care because it can break the normal application flow.
 *
 * @author ARHS Developments - Francesco Cina'
 * @version $Revision
 */
public abstract class APreInitPhasePresenter<T extends IView> extends APresenter<T> {

	public APreInitPhasePresenter(IShowViewStrategy<T> showViewStrategy, IPresenterDefinition presenterDefinition) {
		super(showViewStrategy, presenterDefinition);
	}

	/**
	 * A navigation event launched before the presenter initialization
	 * @param navigationEventsName
	 * @param registerHistory
	 * @param queryStringValues
	 * @param onlyIfTargetNotUsed
	 */
	protected final void preInitLaunchNavigationEvent(String[] navigationEventsName, boolean registerHistory, Map<String, String> queryStringValues) {
		getLogger().info("preInitLaunchNavigationEvent", "event " + navigationEventsName[0] + " execution triggered");
		localLaunchNavigationEvent(navigationEventsName, registerHistory, queryStringValues, false);
	}
	
}
