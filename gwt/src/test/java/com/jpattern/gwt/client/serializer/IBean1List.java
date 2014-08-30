package com.jpattern.gwt.client.serializer;

import java.util.List;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/ago/2011
 */
public interface IBean1List extends GenericList<IBean> {

	@Override
	List<IBean> getList();
	
	String getHello();

}
