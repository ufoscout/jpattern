package com.jpattern.gwt.client;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public abstract class AService {
	
	private IApplicationProvider provider;

	public void setProvider(IApplicationProvider provider) {
		this.provider = provider;
	}
	
	protected IApplicationProvider getProvider() {
		if (provider == null) {
			provider = new NullApplicationProvider();
		}
		return provider;
	}

}
