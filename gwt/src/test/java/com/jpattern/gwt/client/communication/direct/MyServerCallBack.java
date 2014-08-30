package com.jpattern.gwt.client.communication.direct;

import com.jpattern.gwt.client.communication.ICallbackAction;
import com.jpattern.gwt.shared.IWebResultObject;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/mag/2011
 */
public class MyServerCallBack implements ICallbackAction<IWebResultObject> {
	
	public String lastAction = "";

	@Override
	public void onSuccess(IWebResultObject result, int responseCode) {
		lastAction = "SUCCESS";
	}

	@Override
	public void onError(Throwable exception, int responseCode) {
		exception.printStackTrace();
		lastAction = "ERROR";
	}

}
