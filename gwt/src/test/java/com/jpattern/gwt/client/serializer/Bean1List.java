package com.jpattern.gwt.client.serializer;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/ago/2011
 */
public class Bean1List implements IBean1List {

	List<IBean> list = new ArrayList<IBean>();
	String hello = "hello";

	@Override
	public List<IBean> getList() {
		return list;
	}

	@Override
	public String getHello() {
		return hello ;
	}
	
}
