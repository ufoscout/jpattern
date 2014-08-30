package com.jpattern.gwt.property;

import java.util.Map;

import com.jpattern.core.textfiles.IFile;
import com.jpattern.core.textfiles.IFileReader;
import com.jpattern.core.textfiles.IResource;
import com.jpattern.core.textfiles.local.LocalResource;
import com.jpattern.core.util.CharacterEncoding;
import com.jpattern.gwt.client.BaseTest;
import com.jpattern.gwt.client.property.PropertyReader;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/mag/2011
 */
public class PropertyReaderTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testProperty() throws Exception {
		
		String text = "hello=  valuehello \n\n" +
			" hello2  =valuehello2\n" + 
			"text==text with spaces! !  \n" +
			" space space = space space ";
		
		Map<String, String> properties = new PropertyReader(text).getMap();
			
			
		assertEquals( 4 , properties.size() );
		assertTrue( properties.containsKey("hello") );
		assertTrue( properties.containsKey("hello2") );
		assertTrue( properties.containsKey("text") );
		assertTrue( properties.containsKey("space space") );
		assertEquals("valuehello",properties.get("hello"));
		assertEquals("=text with spaces! !",properties.get("text"));
		assertEquals("space space",properties.get("space space"));
		assertEquals("valuehello2",properties.get("hello2"));
	}
	
	
	public void testProperty2() throws Exception {
		IResource resource = new LocalResource(getTestInputBasePath(), CharacterEncoding.UTF_8);
		assertTrue( resource.isValid() );
		
		IFile file = resource.getFile("propertyreader.properties");
		assertTrue(file.exists());
		
		IFileReader fileReader = file.getFileReader();
		String fileContent = fileReader.getFullText();
		fileReader.close();
		
		assertTrue(fileContent.length()>0);
		
		Map<String, String> properties = new PropertyReader(fileContent).getMap();
		
		assertEquals( 4 , properties.size() );
		assertTrue( properties.containsKey("hello") );
		assertTrue( properties.containsKey("hello2") );
		assertTrue( properties.containsKey("text") );
		assertTrue( properties.containsKey("space space") );
		assertEquals("valuehello",properties.get("hello"));
		assertEquals("=text with spaces! !",properties.get("text"));
		assertEquals("space space",properties.get("space space"));
		assertEquals("valuehello2",properties.get("hello2"));
	}

}
