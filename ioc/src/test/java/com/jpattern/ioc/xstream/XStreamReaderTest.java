package com.jpattern.ioc.xstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ListIterator;


import com.jpattern.ioc.xml.BeanContext;
import com.jpattern.ioc.xml.ClassName;
import com.jpattern.ioc.xml.ClassNameConverter;
import com.jpattern.ioc.xml.Context;
import com.jpattern.ioc.xml.Entry;
import com.jpattern.ioc.xml.EntryMap;
import com.jpattern.ioc.xml.Id;
import com.jpattern.ioc.xml.IdConverter;
import com.jpattern.ioc.xml.Key;
import com.jpattern.ioc.xml.Value;
import com.jpattern.ioc.xstream.XStreamReader;
import com.thoughtworks.xstream.XStream;

import junit.framework.TestCase;

public class XStreamReaderTest extends TestCase {

    private XStream _xstream;

    protected void setUp() throws Exception {
        
        super.setUp();
        
        
        _xstream = new XStreamReader().getXStream();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testBeanSerialize() {

        Id id = new Id("id_123");
        ClassName  className = new ClassName("ciaociaosonouna classe");
        BeanContext beanContext = new BeanContext(id, className);

        Context context = new Context();
        context.add(beanContext);
        
        XStream xstream = new XStream();
        xstream.alias("context", Context.class);
        xstream.alias("beancontext", BeanContext.class);
        
        xstream.alias("id", Id.class);
        xstream.alias("classname", ClassName.class);

        xstream.useAttributeFor( BeanContext.class, "id" );
        xstream.registerConverter(new IdConverter());        

        xstream.useAttributeFor( BeanContext.class, "classname" );
        xstream.registerConverter(new ClassNameConverter());
        
        xstream.addImplicitCollection(Context.class, "beanscontext");

        //String expected = "<context>\r\n  <beancontext id=\"id_123\" classname=\"ciaociaosonouna classe\"/>\r\n</context>\r\n";
       
        System.out.println(xstream.toXML(context));
        System.out.println("\r\n");
        System.out.println("\r\n");
        //assertEquals(expected, xstream.toXML(context));
        
    }
    
    
    public void testBeanSerialize2() {
        
        Id id = new Id("pippo");
        ClassName  className = new ClassName("org.jod.pippo");
        BeanContext beanContext = new BeanContext(id, className);
        
        Entry aEntry=  new Entry(new Key("chiave1"), new Value("value1"));
        beanContext.add(aEntry);
        aEntry=  new Entry(new Key("chiave2"), new Value("value2"));
        beanContext.add(aEntry);        
        
        EntryMap aEntryMap = new EntryMap(new Key("mapkey_1"));
        aEntry=  new Entry(new Key("chiave_1_1"), new Value("value_1_1"));
        aEntryMap.add( aEntry );
        aEntry=  new Entry(new Key("chiave_1_2"), new Value("value_1_2"));
        aEntryMap.add( aEntry );
        
        beanContext.add(aEntryMap);
        
        aEntryMap = new EntryMap(new Key("mapkey_2"));
        aEntry=  new Entry(new Key("chiave_2_1"), new Value("value_2_1"));
        aEntryMap.add( aEntry );
        aEntry=  new Entry(new Key("chiave_2_2"), new Value("value_2_2"));
        aEntryMap.add( aEntry );        
        aEntry=  new Entry(new Key("chiave_2_3"), new Value("value_2_3"));
        aEntryMap.add( aEntry );        
        
        beanContext.add(aEntryMap);
        
        boolean b = beanContext.containsEntryProperty("chiave1");
       assertTrue(b);
        Context context = new Context();
        context.add(beanContext);
        
        System.out.println(_xstream.toXML(context));
        
        
    }
    
    public void testDeserializza() throws FileNotFoundException {
        
        Context context = (Context)_xstream.fromXML(  new FileInputStream(new File("./applconf/test.xml")) );
        assertTrue( context.getBeanscontext().size() >0);
        
        List<BeanContext> beans = context.getBeanscontext();
        ListIterator<BeanContext> beansIterator = beans.listIterator();
        BeanContext beanContext;
        
        System.out.println("\r\n\r\n");
        System.out.println(_xstream.toXML(context));
        
        
        while (beansIterator.hasNext()) {
            beanContext = ((BeanContext)beansIterator.next());
            assertTrue(  beanContext.getId().id() != null);
            
            /*
            boolean b = beanContext.containsEntryProperty("nome");
            System.out.println(b);
            assertTrue(b);
            */
        }
    }

}
