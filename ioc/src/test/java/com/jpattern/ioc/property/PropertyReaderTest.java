package com.jpattern.ioc.property;


import com.jpattern.ioc.BaseTest;
import com.jpattern.ioc.ContextBeanFactory;
import com.jpattern.ioc.IPropertyReader;
import com.jpattern.ioc.exception.ConfigException;
import com.jpattern.ioc.property.AbstractPropertyReader;
import com.jpattern.ioc.property.PropertyReader;
import com.jpattern.ioc.property.ReplacerPropertyReader;


public class PropertyReaderTest extends BaseTest {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testIndexOf() {
        
        String aProperty = "${prop3.prop}";
        String prefix = "${";
        String postfix = "}";
        int start = aProperty.indexOf(prefix);
        int stop = aProperty.indexOf(postfix);
        aProperty = aProperty.substring(start + (prefix.length()),stop);
        assertEquals("prop3.prop", aProperty);
    }

    public void testProperty() throws Exception {
        
        AbstractPropertyReader propertyReader = new PropertyReader();
        propertyReader.setLocation(TEST__PROPERTY_FILE);
        propertyReader.load(System.getProperty("user.dir"));
        
        assertEquals( "ciao ciao"  , propertyReader.property("prop1.prop"));
    }
    
    public void testReplaceProperty() throws Exception {
        
        AbstractPropertyReader propertyReader = new ReplacerPropertyReader();
        propertyReader.setLocation(TEST__PROPERTY_FILE);
        propertyReader.load(System.getProperty("user.dir"));
        
        assertEquals( "ciao ciao"  , propertyReader.property("${prop1.prop}"));
        assertEquals( "test replace"  , propertyReader.property("${prop3.prop}"));       
        
    }
    
    public void testWithConfigurator() {
        
        
                try {
                    IPropertyReader reader =  (IPropertyReader)new ContextBeanFactory(TEST_FILE).get("jodreplacer");
                    reader.load(System.getProperty("user.dir"));
                    assertEquals( "ciao ciao"  , reader.property("${prop1.prop}"));
                    assertEquals( "test replace"  , reader.property("${prop3.prop}"));                    
                }
                catch (ConfigException e) {
                    System.err.println();
                }
                
              

        
    }
}
