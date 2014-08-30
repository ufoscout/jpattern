package com.jpattern.core.xml;


import com.jpattern.core.BaseTest;
import com.jpattern.core.xml.IXmlElement;
import com.jpattern.core.xml.XmlElement;

/**
 * 
 * @author Francesco Cina'
 *
 * 18/giu/2010
 */
public class XmlElementTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testXmlElement1() throws Exception {
		IXmlElement xmlRoot = new XmlElement();
		IXmlElement xmlElementChild = xmlRoot.addSubElement();;
		IXmlElement xmlElementTwin1 = xmlRoot.addSubElement();;
		IXmlElement xmlElementTwin2 = xmlRoot.addSubElement();;
		
		xmlElementChild.setName("child");
		xmlElementTwin1.setName("twin");
		xmlElementTwin1.setValue("twin1");
		xmlElementTwin2.setName("twin");
		xmlElementTwin2.setValue("twin2");
		
		assertEquals( 0 , xmlRoot.getSubElements("CIAO").size() );
		assertEquals( 1 , xmlRoot.getSubElements("child").size() );
		assertEquals( 2 , xmlRoot.getSubElements("twin").size() );
		assertEquals( "twin1" , xmlRoot.getSubElements("twin").get(0).getValue() );
		assertEquals( "twin2" , xmlRoot.getSubElements("twin").get(1).getValue() );
		assertEquals( "twin1" , xmlRoot.getSubElement("twin").getValue() );
		
		assertEquals( 2 , xmlRoot.getSubElementsName().size() );
	}

}
