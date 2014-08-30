package com.jpattern.core.xml;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 16/giu/2010
 */
public interface IXmlReaderStrategy extends Serializable {

	void startDocument();

	void endDocument();

	void startNewElement(String name);

	void addAttribute(String name, String value);

	void endElement(String name);

	void addText(String text);

}
