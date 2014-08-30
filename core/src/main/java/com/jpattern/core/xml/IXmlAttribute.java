package com.jpattern.core.xml;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 16/giu/2010
 */
public interface IXmlAttribute extends Serializable {

	String getName();

	void setName(String name);

	String getValue();

	void setValue(String value);

}