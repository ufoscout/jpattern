package com.jpattern.ioc.xml;

import java.util.ArrayList;
import java.util.List;

public class NullBeanContext extends BeanContext {

    private List<IEntry> entries = new ArrayList<IEntry>();
    private List<IParameter> parameters = new ArrayList<IParameter>();
    
    public NullBeanContext() {
        super(new Id(""), new ClassName( "java.lang.String" ));
    }
    
    public void add(IEntry aEntry) {
    }    

    public List<IEntry> getEntries() {
        return entries;
    }
    
    public boolean containsEntryProperty(String properyName) {
        return false;    
    }    
    
    public void add(IParameter aParameter) {
    }    

    public List<IParameter> getParameters() {
        return parameters;
    }
    
    public boolean containsParametersProperty(String properyName) {
        return false;    
    }   
}
