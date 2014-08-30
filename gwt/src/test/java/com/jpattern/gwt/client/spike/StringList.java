package com.jpattern.gwt.client.spike;

import java.util.ArrayList;
import java.util.List;

public class StringList implements IStringList {

	private List<String> list = new ArrayList<String>();

	@Override
	public List<String> getList() {
		return list;
	}

}
