package com.jpattern.core.xml;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Francesco Cina'
 *
 * 16/giu/2010
 */
public interface IXmlElement extends Serializable {

	String getName();

	void setName(String name);

	String getValue();

	void setValue(String value);

	List<String> getSubElementsName();

	IXmlElement getSubElement(String name);
	
	List<IXmlElement> getSubElements(String name);

	IXmlElement addSubElement();

	List<String> getAttributesName();

	IXmlAttribute getAttribute(String name);

	IXmlAttribute addAttribute();

}