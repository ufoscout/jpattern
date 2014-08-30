package com.jpattern.core.xml;

import java.io.File;
import java.io.FileInputStream;

import org.xml.sax.ErrorHandler;


import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.IProvider;
import com.jpattern.core.command.ACommand;
import com.jpattern.core.command.ACommandResult;
import com.jpattern.core.xml.DefaultXmlParserStrategy;
import com.jpattern.core.xml.IXmlElement;
import com.jpattern.core.xml.IXmlReaderStrategy;
import com.jpattern.core.xml.XmlElement;
import com.jpattern.core.xml.XmlReaderCommand;
import com.jpattern.shared.util.GenericWrapper;

/**
 * 
 * @author Francesco Cina'
 *
 * 16/giu/2010
 */
public class XmlReaderCommandTest extends BaseApplicationTest {

	private String xmlCorrettoFilePath;
	private String xmlErratoFilePath;

	protected void setUp() throws Exception {
		super.setUp();
		
		xmlCorrettoFilePath = getTestInputBasePath() + "/xml/xmlTest1.xml";
		xmlErratoFilePath = getTestInputBasePath() + "/xml/xmlTest2_TagNonChiusiCorrettamente.xml";
		assertTrue( new File(xmlCorrettoFilePath).exists() );
		assertTrue( new File(xmlErratoFilePath).exists() );
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testXmlParsing() throws Exception {
		GenericWrapper<Boolean> trimValues = new GenericWrapper<Boolean>(true);
		XmlElement xmlRootElement = new XmlElement();		
		IXmlReaderStrategy xmlParserStrategy = new DefaultXmlParserStrategy( xmlRootElement );
		
		FileInputStream inputStream = new FileInputStream(xmlCorrettoFilePath);
		ErrorHandler errorHandler = new NullErrorHandler();
		ACommand<IProvider> command = new XmlReaderCommand(inputStream, trimValues , xmlParserStrategy, errorHandler);
		ACommandResult result = command.exec(getProvider());
		assertTrue( result.isValid() );
		
		assertEquals( "root_tag" , xmlRootElement.getName() );
		assertEquals( "" , xmlRootElement.getValue() );
		assertEquals( 2 , xmlRootElement.getSubElementsName().size() );
		assertEquals( 0 , xmlRootElement.getAttributesName().size() );
		
		for (String elementName : xmlRootElement.getSubElementsName() ) {
			System.out.println("element " + xmlRootElement.getName() +  " Found child with name: " + elementName);
		}
		
		assertEquals( "subTag1" , xmlRootElement.getSubElementsName().get(0) );
		assertEquals( "anotherSubTag" , xmlRootElement.getSubElementsName().get(1) );
		
		assertEquals( "ciao" , xmlRootElement.getSubElement("anotherSubTag").getValue() );
		
		IXmlElement subTag1 = xmlRootElement.getSubElement("subTag1");
		assertEquals( "subTag1" , subTag1.getName());
		assertEquals( "12345678" , subTag1.getValue());
		assertEquals( 3 , subTag1.getSubElementsName().size() );
		assertEquals( 2 , subTag1.getAttributesName().size() );
		
		for (String elementName : subTag1.getSubElementsName() ) {
			System.out.println("element " + subTag1.getName() +  " Found child with name: " + elementName);
		}
		for (String attributeName : subTag1.getAttributesName() ) {
			System.out.println("element " + subTag1.getName() +  " Found attribute with name: " + attributeName);
		}
		
		assertEquals( "mio valore" , subTag1.getSubElement("subTag11").getSubElement("subTag111").getValue() );
		assertEquals( "mio valore 12" , subTag1.getSubElement("subTag12").getValue() );
		assertEquals( "mio valore 13" , subTag1.getSubElement("subTag13").getValue() );
	}

	
	public void testXmlParsingNoTrim() throws Exception {
		GenericWrapper<Boolean> trimValues = new GenericWrapper<Boolean>(false);
		XmlElement xmlRootElement = new XmlElement();		
		IXmlReaderStrategy xmlParserStrategy = new DefaultXmlParserStrategy( xmlRootElement );
		
		ACommand<IProvider> command = new XmlReaderCommand(new FileInputStream(xmlCorrettoFilePath), trimValues , xmlParserStrategy, new NullErrorHandler());
		ACommandResult result = command.exec(getProvider());
		assertTrue( result.isValid() );
		
		assertEquals( " mio valore " , xmlRootElement.getSubElement("subTag1").getSubElement("subTag11").getSubElement("subTag111").getValue() );
		assertEquals( "mio valore 12" , xmlRootElement.getSubElement("subTag1").getSubElement("subTag12").getValue() );
		assertEquals( "mio valore 13" , xmlRootElement.getSubElement("subTag1").getSubElement("subTag13").getValue() );
	}

	public void testXmlParsingXmlNonCorretto() throws Exception {
		GenericWrapper<Boolean> trimValues = new GenericWrapper<Boolean>(true);
		XmlElement xmlRootElement = new XmlElement();		
		IXmlReaderStrategy xmlParserStrategy = new DefaultXmlParserStrategy( xmlRootElement );
		
		ACommand<IProvider> command = new XmlReaderCommand(new FileInputStream(xmlErratoFilePath), trimValues , xmlParserStrategy, new NullErrorHandler());
		ACommandResult result = command.exec(getProvider());
		assertFalse( result.isValid() );
	}
	
}
