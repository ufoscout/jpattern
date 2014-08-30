package com.jpattern.core.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Francesco Cina'
 *
 * 16/giu/2010
 */
public class XmlElement implements IXmlElement {

	private static final long serialVersionUID = 1L;

	private String name = "";
	private String value = "";
	private List<IXmlElement> subElements = new ArrayList<IXmlElement>();
	private List<IXmlAttribute> attributes = new ArrayList<IXmlAttribute>();
	
	public String getName() {
		if (name == null) {
			name = "";
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		if (value == null) {
			value = "";
		}
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

	public List<String> getSubElementsName() {
		List<String> result = new ArrayList<String>();
		for (IXmlElement subElement : subElements) {
			if ( !result.contains(subElement.getName()) )
			result.add( subElement.getName() );
		}
		return result;
	}
	

	public IXmlElement getSubElement(String name) {
		for (IXmlElement subElement : subElements) {
			if ( subElement.getName().equals(name) ) {
				return subElement;
			}
		}
		return new XmlElement();
	}
	

	public List<IXmlElement> getSubElements(String name) {
		List<IXmlElement> result = new ArrayList<IXmlElement>();
		for (IXmlElement subElement : subElements) {
			if ( subElement.getName().equals(name) ) {
				result.add(subElement);
			}
		}
		return result;
	}
	
	public IXmlElement addSubElement() {
		IXmlElement element = new XmlElement();
		subElements.add(element);
		return element;
	}
	
	public List<String> getAttributesName() {
		List<String> result = new ArrayList<String>();
		for (IXmlAttribute attribute: attributes) {
			result.add( attribute.getName() );
		}
		return result;
	}
	
	public IXmlAttribute getAttribute(String name) {
		for (IXmlAttribute attribute : attributes) {
			if ( attribute.getName().equals(name) ) {
				return attribute;
			}
		}
		return new XmlAttribute();
	}
	
	public IXmlAttribute addAttribute() {
		IXmlAttribute attribute = new XmlAttribute();
		this.attributes.add( attribute );
		return attribute;
	}

}
