package com.jpattern.gwt.client.view;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author Francesco Cina
 *
 * 02/ago/2011
 */
public class NullHasWidgets implements HasWidgets {

	@Override
	public void add(Widget w) {
	}

	@Override
	public void clear() {
	}

	@Override
	public Iterator<Widget> iterator() {
		return new ArrayList<Widget>().iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return false;
	}

}
