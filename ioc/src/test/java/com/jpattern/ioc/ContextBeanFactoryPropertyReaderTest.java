package com.jpattern.ioc;


import com.jpattern.ioc.ContextBeanFactory;
import com.jpattern.ioc.model.xml.BeanUno;

/**
 * 
 * @author Francesco Cina'
 *
 * 06/dic/2010
 */
public class ContextBeanFactoryPropertyReaderTest extends BaseTest {

	private String testFile = "./applconf/test-multi-reader.xml";
	
    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testBeanFactory()  throws Exception  {
        Object beanObject = new ContextBeanFactory(testFile).get("beanuno");

        BeanUno uno = (BeanUno)beanObject;
        
        assertEquals( "mago" , uno.getNome());
        assertEquals( "normal mago" , uno.getCognome());
        assertEquals( "normal test replace" , uno.getAttributereplaced());
        assertEquals( "pippo" , uno.getAttributereplacedcrypted());
        
    } 
    
}