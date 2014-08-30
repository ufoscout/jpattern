package com.jpattern.gwt.client.spike;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/ago/2011
 */
public interface BeanFactory extends AutoBeanFactory {

	AutoBean<IBean> bean();
	
	AutoBean<IBean> bean(IBean bean);
	
	AutoBean<IBeanList> beanList();
	
	AutoBean<IBeanList> beanList(IBeanList list);
	
	AutoBean<IStringList> stringList();
	
	AutoBean<IStringList> stringList(IStringList list);
	
}
