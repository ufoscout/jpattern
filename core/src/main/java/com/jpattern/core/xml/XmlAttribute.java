package com.jpattern.core.xml;

/**
 * 
 * @author Francesco Cina'
 *
 * 16/giu/2010
 */
public class XmlAttribute implements IXmlAttribute {

	private static final long serialVersionUID = 1L;
	
	private String name = "";
	private String value = "";
	
	/* (non-Javadoc)
	 * @see org.jod5.base.xml.IXmlAttribute#getName()
	 */
	public String getName() {
		if (name == null) {
			name = "";
		}
		return name;
	}
	/* (non-Javadoc)
	 * @see org.jod5.base.xml.IXmlAttribute#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see org.jod5.base.xml.IXmlAttribute#getValue()
	 */
	public String getValue() {
		if (value == null) {
			value = "";
		}
		return value;
	}
	/* (non-Javadoc)
	 * @see org.jod5.base.xml.IXmlAttribute#setValue(java.lang.String)
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
