package com.jpattern.gwt.client.serializer;

import com.google.web.bindery.autobean.shared.AutoBean;
/**
 * 
 * @author Francesco Cina'
 *
 * 23/ago/2011
 */
public interface ITestBean1ListFactory extends IBeanFactory {

	AutoBean<IBean> bean1();
	
	AutoBean<IBean> bean1(IBean obj);
	
	AutoBean<IBean1List> bean1List();
	
	AutoBean<IBean1List> bean1List(IBean1List obj);
	
	
	
}
