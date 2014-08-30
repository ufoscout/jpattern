package com.jpattern.ioc.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 23/ott/08 17:53:34
 * @version $Id: $
 */
public class BeanContext {

    private List<IEntry> entries = new ArrayList<IEntry>();
    private List<IParameter> parameters = new ArrayList<IParameter>();

    public BeanContext(Id aId, ClassName aClassName) {

        id = aId;
        classname = aClassName;
    }

    public void add(IEntry aEntry) {
        entries.add(aEntry);
    }
    
    public void add(IParameter aParameter) {
        parameters.add(aParameter);
    }


    private Id id;

    private ClassName classname;

    public Id getId() {
        return id;
    }

    public ClassName getClassname() {
        return classname;
    }

    public List<IEntry> getEntries() {
        if (entries == null)
            entries = new ArrayList<IEntry>();
        return entries;
    }
    
    public List<IParameter> getParameters() {
        if (parameters == null)
        	parameters = new ArrayList<IParameter>();
        return parameters;
    }

    /*
     * public boolean containsEntryProperty(String properyName) { ListIterator liter = getEntries().listIterator(); IEntry iEntry ; Key key; while
     * (liter.hasNext()) { iEntry = (IEntry)liter.next(); key= iEntry.getKey(); if (properyName.equals(key.key())) return true; } return false; }
     */
    
    
    public boolean containsEntryProperty(String properyName) {
        return getEntries().contains(new Entry(new Key(properyName), null));
    }
    
    public int hashCode() {
        return id.hashCode();
    }

    public boolean equals(Object obj) {
        if ( !(obj instanceof BeanContext))
                return false;
        final BeanContext other = (BeanContext) obj;
        return id.equals(other.id);
    }
}
