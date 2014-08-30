package com.jpattern.gwt.shared;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.jpattern.gwt.client.serializer.IBeanFactory;

/**
 * 
 * @author Francesco Cina'
 *
 */
public interface ITestBeanFactory extends IBeanFactory {
	
	AutoBean<IResultObject> resultObject();
	AutoBean<IResultObject> resultObject(IResultObject resultObject);
	
	AutoBean<IWebResultString> webResultString();
	AutoBean<IWebResultString> webResultString(IWebResultString toWrap);
	
	AutoBean<IWebResultResultObject> webResultResultObject();
	AutoBean<IWebResultResultObject> webResultResultObject(IWebResultResultObject toWrap);
	 
}
