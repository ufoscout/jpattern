package com.jpattern.ioc.xml;

import java.util.ArrayList;
import java.util.List;


import com.jpattern.ioc.BaseTest;
import com.jpattern.ioc.xml.Entry;
import com.jpattern.ioc.xml.EntryMap;
import com.jpattern.ioc.xml.EntryRef;
import com.jpattern.ioc.xml.IEntry;
import com.jpattern.ioc.xml.Key;
import com.jpattern.ioc.xml.Value;


public class EntryTest extends BaseTest {

 

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testContains() throws Exception {
        
        List<IEntry> entries = new ArrayList<IEntry>();
        
        IEntry aEntry = new Entry(new Key("a"), new Value("value_a"));
        entries.add(aEntry);

        aEntry = new EntryMap(new Key("b"));
        entries.add(aEntry);        
        
        aEntry = new EntryRef(new Key("c"), new Value("value_c"));
        entries.add(aEntry);                
        
        
        IEntry testEntryYes = new Entry(new Key("a"), null);
        
        assertTrue(entries.contains(testEntryYes));
        
        IEntry testEntryNo = new Entry(new Key("d"), null);
        
        assertFalse(entries.contains(testEntryNo));
    }
}
