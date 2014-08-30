package com.jpattern.gwt.client.view;

import com.google.gwt.user.client.ui.HasVisibility;

/**
 * 
 * @author Francesco Cina'
 *
 * Nov 28, 2011
 */
public class Visible implements HasVisibility {

	private boolean visible = true;

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
