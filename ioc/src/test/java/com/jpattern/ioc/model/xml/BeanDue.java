package com.jpattern.ioc.model.xml;

/**
 * 
 * @author Francesco Cina'
 *
 * 21/dic/2009
 */
public class BeanDue {
	
	String stringa;
	
	public String getResult() {
		return stringa;
	}
	
	public void setStringa(String stringa) {
		this.stringa = "setter con String";
	}
	
	public void setInteger(Integer intero) {
		this.stringa = "setter con Integer";
	}
	
	public void setInt(int intero){
		this.stringa = "setter con int";
	}
}
