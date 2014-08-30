package com.jpattern.ioc.reflection;


import java.io.File;
import java.io.FileInputStream;


import com.jpattern.ioc.AbstractReader;
import com.jpattern.ioc.BaseTest;
import com.jpattern.ioc.BeanMapper;
import com.jpattern.ioc.ContextCreator;
import com.jpattern.ioc.IContextCreator;
import com.jpattern.ioc.model.xml.BeanUno;
import com.jpattern.ioc.model.xml.Residenza;
import com.jpattern.ioc.reflection.ReflectionUtil;
import com.jpattern.ioc.util.FilePath;
import com.jpattern.ioc.xml.Context;
import com.jpattern.ioc.xstream.XStreamReader;

public class BeanContextReflectionTest extends BaseTest {

    private AbstractReader _reader;

    private Context _context;

    private BeanMapper _beanMapper;

    protected void setUp() throws Exception {
        super.setUp();

        _reader = new XStreamReader();
        _context = new Context();
        _context.init(_reader, new FileInputStream(new File(TEST_FILE)), FilePath.getPath(TEST_FILE));
//        	_reader.read( new FileInputStream(new File(TEST_FILE)) );
        _beanMapper = new BeanMapper();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBeanContextReflectionWithObject() throws Exception {
            ReflectionUtil reflectionUtil = new ReflectionUtil();
            IContextCreator contextCreator = new ContextCreator(reflectionUtil, _beanMapper, _context);
            Object beanObject = contextCreator.create("beanuno");            
            assertForEachObject(beanObject);
    }

    private void assertForEachObject(Object beanObject) {

        BeanUno uno = (BeanUno) beanObject;

        assertEquals("beppe", uno.getNome());
        assertEquals("signori", uno.getCognome());

        assertTrue(!uno.getMapmezzi().isEmpty());
        assertTrue(!uno.getMaptelefoni().isEmpty());

        Residenza residenza = uno.getResidenza();
        
        assertEquals("via di casa mia", residenza.getIndirizzo());
    }
}
