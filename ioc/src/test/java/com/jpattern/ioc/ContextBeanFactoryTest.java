package com.jpattern.ioc;

import java.util.List;
import java.util.Map;


import com.jpattern.ioc.ContextBeanFactory;
import com.jpattern.ioc.model.xml.BeanDue;
import com.jpattern.ioc.model.xml.BeanListUno;
import com.jpattern.ioc.model.xml.BeanUno;
import com.jpattern.ioc.model.xml.ManyConstructors;
import com.jpattern.ioc.model.xml.Residenza;


public class ContextBeanFactoryTest extends BaseTest {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testBeanFactory()  throws Exception  {
        Object returned = new ContextBeanFactory(TEST_FILE).get("beanuno");
        assertForEachObject(returned);
    }
    
    private void assertForEachObject(Object beanObject) {

        BeanUno uno = (BeanUno)beanObject;
        
        assertEquals( "beppe" , uno.getNome());
        assertEquals( "signori" , uno.getCognome());
        assertEquals( "test replace" , uno.getAttributereplaced());
        assertEquals( "pippo" , uno.getAttributereplacedcrypted());
        
        assertTrue( !uno.getMapmezzi().isEmpty() );
        assertTrue( !uno.getMaptelefoni().isEmpty() );
        
        Residenza residenza = uno.getResidenza();
        assertEquals("via di casa mia", residenza.getIndirizzo());        
        
        Map<?,?> mezzi = uno.getMapmezzi();
        Residenza residenzadamappa = (Residenza)mezzi.get("casa");
        assertNotNull(residenzadamappa);
        
        assertEquals("via di casa sua", residenzadamappa.getIndirizzo());
        Residenza residenzamiadamappa = (Residenza)mezzi.get("casamia");
        assertNotNull(residenzamiadamappa);
        assertEquals("via di casa mia", residenzamiadamappa.getIndirizzo());        
        
        // residenza e residenzamiadamappa devono essere uguali sono la stessa instanza
        assertEquals( residenza ,residenzamiadamappa);

        // residenza e residenzadamappa devono essere diversi sono istanze diverse
        assertNotSame( residenza ,residenzadamappa);
    } 
    
    
    public void testBeanFactory2()  throws Exception  {
    	// istanzio da costruttore con 2 parametri String String
        Object returned = new ContextBeanFactory(TEST_FILE).get("beanconparametri1");
        BeanUno uno = (BeanUno) returned;
        System.out.println( uno.getNome() );
        assertEquals( "nomeDaCostruttore" , uno.getNome());
        assertEquals( "cognomeDaCostruttore" , uno.getCognome());
        assertEquals( "via di casa mia" , uno.getResidenza().getIndirizzo());
        
        
        // istanzio da costruttore con 3 parametri String String Map
        returned = new ContextBeanFactory(TEST_FILE).get("beanconparametri2");
        uno = (BeanUno) returned;
        System.out.println( uno.getNome() );
        assertEquals( "nomeDaCostruttore2" , uno.getNome());
        assertEquals( "cognomeDaCostruttore2" , uno.getCognome());
        assertEquals( "via di casa sua" , uno.getResidenza().getIndirizzo());
        
        
        // istanzio da costruttore con 3 parametri String String Reference
        returned = new ContextBeanFactory(TEST_FILE).get("beanconparametri3");
        uno = (BeanUno) returned;
        System.out.println( uno.getNome() );
        assertEquals( "nomeDaCostruttore3" , uno.getNome());
        assertEquals( "cognomeDaCostruttore3" , uno.getCognome());
        assertNull( uno.getResidenza() );
        assertTrue( !uno.getMapmezzi().isEmpty() );
        assertEquals( "honda hornet" , (String) uno.getMapmezzi().get("moto") );
    }
    
    public void testBeanFactory3()  throws Exception  {
    	// istanzio da costruttore con parametro Integer
        Object returned = new ContextBeanFactory(TEST_FILE).get("beanConInteger");
        ManyConstructors uno = (ManyConstructors) returned;
        System.out.println("Usato costruttore " + uno.getQualeCostruttore() + " (Integer)");
        System.out.println(uno.getDettagli());
        assertEquals( 3 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro Integer = 17" , uno.getDettagli());
        
    	// istanzio da costruttore con parametro Double
        returned = new ContextBeanFactory(TEST_FILE).get("beanConDouble");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 7 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro Double = 17.0" , uno.getDettagli());
        
    	// istanzio da costruttore con parametro Byte
        returned = new ContextBeanFactory(TEST_FILE).get("beanConByte");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 8 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro Byte = 17" , uno.getDettagli());
        
    	// istanzio da costruttore con parametro Short
        returned = new ContextBeanFactory(TEST_FILE).get("beanConShort");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 9 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro Short = 17" , uno.getDettagli());
        
    	// istanzio da costruttore con parametro Long
        returned = new ContextBeanFactory(TEST_FILE).get("beanConLong");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 10 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro Long = 17" , uno.getDettagli());
        
    	// istanzio da costruttore con parametro Float
        returned = new ContextBeanFactory(TEST_FILE).get("beanConFloat");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 11 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro Float = 17.0" , uno.getDettagli());
        
    	// istanzio da costruttore con parametro Boolean
        returned = new ContextBeanFactory(TEST_FILE).get("beanConBoolean");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 12 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro Boolean = true" , uno.getDettagli());
        
    	// istanzio da costruttore con parametro Character
        returned = new ContextBeanFactory(TEST_FILE).get("beanConCharacter");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 13 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro Character = 1" , uno.getDettagli());
        
    	// istanzio da costruttore con parametro BigInteger
        returned = new ContextBeanFactory(TEST_FILE).get("beanConBigInteger");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 14 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro BigInteger = 17" , uno.getDettagli());
        
    	// istanzio da costruttore con parametro BigDecimal
        returned = new ContextBeanFactory(TEST_FILE).get("beanConBigDecimal");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 15 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro BigDecimal = 17" , uno.getDettagli());
        
    	// istanzio da costruttore con parametro StringBuffer
        returned = new ContextBeanFactory(TEST_FILE).get("beanConStringBuffer");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 16 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro StringBuffer = 17" , uno.getDettagli());
        
    	// istanzio da costruttore con 5 parametri
        returned = new ContextBeanFactory(TEST_FILE).get("beanCon5Parametri");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 17 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 5 parametri StringBuffer=ciaociao, BigInteger=12345, Character=u, Boolean=true, String=stringa" , uno.getDettagli());
    }
    
    
    public void testBeanFactory4() throws Exception  {
        BeanDue beandue1 = (BeanDue) new ContextBeanFactory(TEST_FILE).get("beandue1");
        BeanDue beandue2 = (BeanDue) new ContextBeanFactory(TEST_FILE).get("beandue2");
        BeanDue beandue3 = (BeanDue) new ContextBeanFactory(TEST_FILE).get("beandue3");
        BeanDue beandue4 = (BeanDue) new ContextBeanFactory(TEST_FILE).get("beandue4");
        BeanDue beandue5 = (BeanDue) new ContextBeanFactory(TEST_FILE).get("beandue5");
        
        assertEquals( "setter con String" , beandue1.getResult() );
        assertEquals( "setter con Integer" , beandue2.getResult() );
        assertEquals( "setter con Integer" , beandue3.getResult() );
        assertEquals( "setter con int" , beandue4.getResult() );
        assertEquals( "setter con int" , beandue5.getResult() );
    }

    public void testBeanFactory5() throws Exception  {
        BeanListUno beanList = (BeanListUno) new ContextBeanFactory(TEST_FILE).get("beanListUno1");
        
        assertNotNull( beanList );
        assertNotNull( beanList.getValori() );
        assertNotNull( beanList.getValori2() );
        
        for ( int i=0; i<beanList.getValori().size(); i++ ) {
        	System.out.println( "getStringhe() -> valore: " + beanList.getValori().get(i).toString() );
        }
        assertEquals( 6 , beanList.getValori().size() );
        
        for ( int i=0; i<beanList.getValori2().size(); i++ ) {
        	System.out.println( "getInteri() -> valore: " + beanList.getValori2().get(i).toString() );
        	assertTrue( beanList.getValori2().get(i) instanceof Integer );
        }
        
        assertTrue( beanList.getValori2().get(0).toString().equals("10") );
        assertTrue( beanList.getValori2().get(1).toString().equals("20") );
        assertEquals( 2 , beanList.getValori2().size() );
    }
    
	public void testBeanFactory6() throws Exception  {
        BeanListUno beanList = (BeanListUno) new ContextBeanFactory(TEST_FILE).get("beanListUno2");
        
        assertNotNull( beanList );
        assertNotNull( beanList.getValori() );
        assertEquals( 4 , beanList.getValori().size() );
        assertTrue( beanList.getValori().get(0) instanceof List );
        assertTrue( beanList.getValori().get(1) instanceof Map );
        assertTrue( beanList.getValori().get(2) instanceof ManyConstructors );
        assertTrue( beanList.getValori().get(3) instanceof String );
    }
    
	public void testBeanFactory_CostruttoriConList()  throws Exception  {
        BeanListUno beanList = (BeanListUno) new ContextBeanFactory(TEST_FILE).get("beanListUno3");
        
        assertNotNull( beanList );
        assertNotNull( beanList.getValori2() );
        assertNotNull( beanList.getValori() );
        assertEquals( 4 , beanList.getValori().size() );
        assertTrue( beanList.getValori().get(0) instanceof List );
        System.out.println( beanList.getValori().get(0).toString() );
        assertTrue( beanList.getValori().get(1) instanceof Map );
        System.out.println( beanList.getValori().get(1).toString() );
        assertTrue( beanList.getValori().get(2) instanceof ManyConstructors );
        System.out.println( beanList.getValori().get(2).toString() );
        assertTrue( beanList.getValori().get(3) instanceof String );
        System.out.println( beanList.getValori().get(3).toString() );
    }
    
    public void testBeanFactory_CostruttoriConPrimitive()  throws Exception  {
    	// istanzio da costruttore con parametro int
        Object returned = new ContextBeanFactory(TEST_FILE).get("beanCon_int");
        ManyConstructors uno = (ManyConstructors) returned;
        System.out.println("Usato costruttore " + uno.getQualeCostruttore() + " (int)");
        System.out.println(uno.getDettagli());
        assertEquals( 2 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro int = 123" , uno.getDettagli());
        
        // istanzio da costruttore con parametro int con value non valido
        returned = new ContextBeanFactory(TEST_FILE).get("beanCon_int_errato");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 2 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro int = 0" , uno.getDettagli());
        
        // istanzio da costruttore con parametro double
        returned = new ContextBeanFactory(TEST_FILE).get("beanCon_double");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 4 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro double = 124.0" , uno.getDettagli());
        
        // istanzio da costruttore con parametro short
        returned = new ContextBeanFactory(TEST_FILE).get("beanCon_short");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 18 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro short = 125" , uno.getDettagli());
        
        // istanzio da costruttore con parametro byte
        returned = new ContextBeanFactory(TEST_FILE).get("beanCon_byte");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 19 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro byte = 126" , uno.getDettagli());
        
        // istanzio da costruttore con parametro long
        returned = new ContextBeanFactory(TEST_FILE).get("beanCon_long");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 20 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro long = 127" , uno.getDettagli());
        
        // istanzio da costruttore con parametro long
        returned = new ContextBeanFactory(TEST_FILE).get("beanCon_float");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 21 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro float = 128.65" , uno.getDettagli());
        
        // istanzio da costruttore con parametro boolean
        returned = new ContextBeanFactory(TEST_FILE).get("beanCon_boolean");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 22 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro boolean = true" , uno.getDettagli());
        
        // istanzio da costruttore con parametro long
        returned = new ContextBeanFactory(TEST_FILE).get("beanCon_char");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 23 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 1 parametro char = p" , uno.getDettagli());
        
        // istanzio da costruttore con 5 parametri fra cui dei tipi primitive
        returned = new ContextBeanFactory(TEST_FILE).get("beanCon5ParametriEPrimitive");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 24 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 5 parametri StringBuffer=ciaociao, int=12345, Character=u, boolean=true, String=stringa" , uno.getDettagli());
        
     // istanzio da costruttore con 5 parametri fra cui dei tipi primitive
        returned = new ContextBeanFactory(TEST_FILE).get("beanCon6ParametriEPrimitive");
        uno = (ManyConstructors) returned;
        System.out.println(uno.getDettagli());
        assertEquals( 25 , uno.getQualeCostruttore());
        assertEquals( "costruttore con 6 parametri StringBuffer=ciaociao, int=12345, Character=u, boolean=true, String=stringa, Object=stringa" , uno.getDettagli());
    }
    
}
