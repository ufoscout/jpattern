package com.jpattern.core.xml;

import java.util.Stack;

/**
 * 
 * @author Francesco Cina'
 *
 * 16/giu/2010
 */
public class DefaultXmlParserStrategy implements IXmlReaderStrategy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IXmlElement xmlRootElement;
	private Stack<IXmlElement> elementsStack = new Stack<IXmlElement>(); 

	
	public DefaultXmlParserStrategy(IXmlElement xmlRootElement) {
		this.xmlRootElement = xmlRootElement;
	}
	
	public void addAttribute(String name, String value) {
		if ( !elementsStack.isEmpty()) {
			IXmlAttribute attribute = elementsStack.peek().addAttribute(  );
			attribute.setName(name);
			attribute.setValue(value);
			
		}
	}

	public void endDocument() {
	}

	public void endElement(String name) {
		if ( !elementsStack.isEmpty()) {
			elementsStack.pop();
		}
	}

	public void startDocument() {
		elementsStack.clear();
	}

	public void startNewElement(String name) {
		if ( elementsStack.isEmpty()) {
			elementsStack.add(xmlRootElement);
		}
		else {
			elementsStack.push( elementsStack.peek().addSubElement() );
		}
		elementsStack.peek().setName(name);
	}

	public void addText(String text) {
		if ( !elementsStack.isEmpty()) {
			elementsStack.peek().setValue( elementsStack.peek().getValue() + text );
		}
	}

}
