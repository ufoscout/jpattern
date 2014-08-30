package com.jpattern.gwt.client.spike;

import java.util.ArrayList;
import java.util.List;

public class BeanList implements IBeanList {

	private List<IBean> list = new ArrayList<IBean>();

	@Override
	public List<IBean> getList() {
		return list;
	}

}
