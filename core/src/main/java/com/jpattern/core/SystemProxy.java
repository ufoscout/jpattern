package com.jpattern.core;


/**
*
* @author ARHS Developments
*         Francesco Cina' - 29/giu/2011 
* @version $Revision
 */
public class SystemProxy implements ISystemProxy<IProvider> {

	private static ISystemProxy<IProvider> PROXY = null;
	private final ISystem applicationService;
	
	public SystemProxy(ISystem applicationService) {
        this.applicationService = applicationService;
		PROXY = this;
	}
	
	@Override
	public IProvider getProvider() {
	    return applicationService;
	}

	@Override
	public ISystem getSystem() {
		return applicationService;
	}   

	public static ISystemProxy<IProvider> proxy() {
		return PROXY;
	}
}
