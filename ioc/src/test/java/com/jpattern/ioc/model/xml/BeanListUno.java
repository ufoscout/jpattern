package com.jpattern.ioc.model.xml;

import java.util.List;

/**
 * 
 * @author Francesco Cina'
 *
 * 21/dic/2009
 */
public class BeanListUno {
	
	private String stringa;
	private List<?> valori;
	private List<?> valori2;
	
	public BeanListUno() {
	}

	public BeanListUno( List<?> valori ) {
		this.valori = valori;
	}
	
	public String getResult() {
		return stringa;
	}
	
	public void setValoriList(List<?> valori) {
		this.valori = valori; 
		this.stringa = "setValoriList";
	}
	
	public void setValori2List(List<?> valori) {
		this.valori2 = valori;
		this.stringa = "setValori2List";
	}
	
	public List<?> getValori() {
		return valori;
	}
	
	public List<?> getValori2() {
		return valori2;
	}
	
	public String getStringa() {
		return stringa;
	}
}
