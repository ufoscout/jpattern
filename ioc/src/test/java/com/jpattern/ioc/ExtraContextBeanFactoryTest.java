package com.jpattern.ioc;


import com.jpattern.ioc.ContextBeanFactory;
import com.jpattern.ioc.model.xml.Residenza;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/nov/2010
 */
public class ExtraContextBeanFactoryTest extends BaseTest {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testExtraContext1()  throws Exception  {
        Residenza returned = (Residenza) new ContextBeanFactory(TEST_FILE).get("residenza-extra1");
        assertNotNull(returned);
        assertEquals( "indirizzo-residenza-extra1" , returned.getIndirizzo() );
    }
    
    public void testExtraContext1_1()  throws Exception  {
        Residenza returned = (Residenza) new ContextBeanFactory(TEST_FILE).get("residenza-extra1_1");
        assertNotNull(returned);
        assertEquals( "indirizzo-residenza-extra1_1" , returned.getIndirizzo() );
    }
    
    public void testExtraContext2()  throws Exception  {
        Residenza returned = (Residenza) new ContextBeanFactory(TEST_FILE).get("residenza-extra2");
        assertNotNull(returned);
        assertEquals( "indirizzo-residenza-extra2" , returned.getIndirizzo() );
    }
    
}
