package com.jpattern.gwt.client.serializer;

import com.google.web.bindery.autobean.shared.AutoBean;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/ago/2011
 */
public interface ITestBean1Factory extends IBeanFactory {

//	AutoBean<IBean1> bean1();
//	
//	AutoBean<IBean1> bean1(IBean1 obj);
	
	AutoBean<IBean1Holder> beanHolder();
	
	AutoBean<IBean1Holder> beanHolder(IBean1Holder obj);
	
}
