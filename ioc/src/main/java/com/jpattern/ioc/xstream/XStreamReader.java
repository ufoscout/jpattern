package com.jpattern.ioc.xstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;



import com.jpattern.ioc.AbstractReader;
import com.jpattern.ioc.xml.BeanContext;
import com.jpattern.ioc.xml.ClassName;
import com.jpattern.ioc.xml.ClassNameConverter;
import com.jpattern.ioc.xml.Context;
import com.jpattern.ioc.xml.Entry;
import com.jpattern.ioc.xml.EntryList;
import com.jpattern.ioc.xml.EntryMap;
import com.jpattern.ioc.xml.EntryRef;
import com.jpattern.ioc.xml.ExtraContext;
import com.jpattern.ioc.xml.IEntry;
import com.jpattern.ioc.xml.IParameter;
import com.jpattern.ioc.xml.Id;
import com.jpattern.ioc.xml.IdConverter;
import com.jpattern.ioc.xml.Key;
import com.jpattern.ioc.xml.KeyConverter;
import com.jpattern.ioc.xml.Parameter;
import com.jpattern.ioc.xml.ParameterList;
import com.jpattern.ioc.xml.ParameterMap;
import com.jpattern.ioc.xml.ParameterRef;
import com.jpattern.ioc.xml.Type;
import com.jpattern.ioc.xml.TypeConverter;
import com.jpattern.ioc.xml.Value;
import com.jpattern.ioc.xml.ValueConverter;
import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 24/ott/08 11:27:02
 * @version $Id: $
 */
public class XStreamReader extends AbstractReader{

    private XStream _xstream;


    public XStreamReader() {
                setup();
    }
    
    protected XStream getXStream() {
    	return _xstream;
    }
    
    public Context read(String apath) throws FileNotFoundException {
        return  (Context)_xstream.fromXML(  new FileInputStream(new File(apath)) );
        
    }

    public Context read(InputStream aInputStream) {
        return  (Context)_xstream.fromXML(aInputStream);
    }
    
	public void rdead(Context context, String apath) throws FileNotFoundException {
		context.addBeanContextList(read(apath).getBeanscontext());
	}

	public void read(Context context, InputStream aInputStream) {
		Context readedContext = (Context)_xstream.fromXML(aInputStream);
		context.addBeanContextList(readedContext.getBeanscontext());
		context.addExtraContextList(readedContext.getExtracontext());
	}
    
    private void setup() {
        
        _xstream = new  XStream();
        _xstream.alias("context", Context.class);
        _xstream.alias("beancontext", BeanContext.class);
        _xstream.alias("extracontext", ExtraContext.class);
        
        _xstream.alias("parameter", Parameter.class);
        _xstream.alias("parametermap", ParameterMap.class);
        _xstream.alias("parameterref", ParameterRef.class);
        _xstream.alias("parameterlist", ParameterList.class);
        
        _xstream.alias("entry", Entry.class);
        _xstream.alias("entrymap", EntryMap.class);
        _xstream.alias("entryref", EntryRef.class);
        _xstream.alias("entrylist", EntryList.class);
        
        _xstream.alias("id", Id.class);
        _xstream.alias("classname", ClassName.class);
        
        _xstream.alias("key", Key.class);
        _xstream.alias("value", Value.class);
        _xstream.alias("type", Type.class);
        
        _xstream.useAttributeFor( BeanContext.class, "id" );
        _xstream.registerConverter(new IdConverter());        

        _xstream.useAttributeFor( BeanContext.class, "classname" );
        _xstream.registerConverter(new ClassNameConverter());
        
        _xstream.useAttributeFor( ExtraContext.class, "value" ); 
        _xstream.registerConverter(new ValueConverter());    
        
        _xstream.useAttributeFor( Entry.class, "key" );
        _xstream.registerConverter(new KeyConverter());        

        _xstream.useAttributeFor( Entry.class, "value" );
        _xstream.registerConverter(new ValueConverter());
        
        _xstream.useAttributeFor( Entry.class, "type" );
        _xstream.registerConverter(new TypeConverter());
        
        _xstream.useAttributeFor( Parameter.class, "value" );
        _xstream.registerConverter(new ValueConverter());
        
        _xstream.useAttributeFor( Parameter.class, "type" );
        _xstream.registerConverter(new TypeConverter());
        
        _xstream.useAttributeFor( EntryRef.class, "key" ); 
        _xstream.registerConverter(new KeyConverter());        

        _xstream.useAttributeFor( EntryRef.class, "value" ); 
        _xstream.registerConverter(new ValueConverter());         
        
        _xstream.useAttributeFor( ParameterRef.class, "value" ); 
        _xstream.registerConverter(new ValueConverter());  
        
        _xstream.useAttributeFor( EntryMap.class, "key" );
        _xstream.registerConverter(new KeyConverter());
        
        _xstream.useAttributeFor( EntryList.class, "key" );
        _xstream.registerConverter(new KeyConverter()); 
        
        _xstream.addImplicitCollection(Context.class, "beanscontext", BeanContext.class);
        _xstream.addImplicitCollection(Context.class, "extracontext", ExtraContext.class);
        _xstream.addImplicitCollection(BeanContext.class, "entries", IEntry.class);
        _xstream.addImplicitCollection(BeanContext.class, "parameters", IParameter.class);
        _xstream.addImplicitCollection(EntryMap.class, "entries");
        _xstream.addImplicitCollection(EntryList.class, "entries"); 
        _xstream.addImplicitCollection(ParameterMap.class, "entries");
        _xstream.addImplicitCollection(ParameterList.class, "entries");
    }

}
