package com.jpattern.ioc.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.jpattern.ioc.AbstractReader;
import com.jpattern.ioc.ContextCreator;
import com.jpattern.ioc.IContextCreator;
import com.jpattern.ioc.IPropertyReader;
import com.jpattern.ioc.property.NullPropertyReader;
import com.jpattern.ioc.util.FilePath;

/**
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 23/ott/08 17:53:19
 * @version 2.0  Francesco Cina - 27/nov/10
 * @version $Id: $
 */
public class Context {

    private List<BeanContext> beanscontext = new ArrayList<BeanContext>();
    private List<ExtraContext> extracontext = new ArrayList<ExtraContext>();
    private List<Context> subContextList = new ArrayList<Context>();
	private String basePath;

    public void add(BeanContext aObject) {
        beanscontext.add(aObject);
    }

    public void add(ExtraContext aExtraContext) {
        extracontext.add(aExtraContext);
    }
    
    public void addBeanContextList(List<BeanContext> beanscontextList) {
    	for (int i=0; i<beanscontextList.size(); i++) {
    		if (beanscontextList.get(i) instanceof BeanContext) {
    			add((BeanContext) beanscontextList.get(i));
    		}
    	}
    }
    
	public void addExtraContextList(List<ExtraContext> extracontextList) {
    	for (int i=0; i<extracontextList.size(); i++) {
    		if (extracontextList.get(i) instanceof ExtraContext) {
    			add((ExtraContext) extracontextList.get(i));
    		}
    	}
	}
    
    public List<BeanContext> getBeanscontext() {
    	if (beanscontext==null) {
    		beanscontext = new ArrayList<BeanContext>();
    	}
        return beanscontext;
    }
    
    public List<ExtraContext> getExtracontext() {
    	if (extracontext==null) {
    		extracontext = new ArrayList<ExtraContext>();
    	}
        return extracontext;
    }

    public BeanContext get(String beanContextId) {
        int i = beanscontext.indexOf(new BeanContext(new Id(beanContextId), null));
        if (i != -1) {
            return beanscontext.get(i);
        }
        for ( int sub=0; sub<subContextList.size(); sub++) {
        	BeanContext subResult = subContextList.get(sub).get(beanContextId);
        	if (subResult!=null) {
        		return subResult;
        	}
        }
        return null;
    }

	public void init(AbstractReader reader, InputStream aInputStream, String basePath) throws IOException {
		this.basePath = basePath;
		reader.read(this, aInputStream);
		for (int i=0; i<extracontext.size(); i++) {
			Context subContext = new Context();
			File file = new File(basePath + File.separator + extracontext.get(i).getValue().value());
			InputStream fileInputStream = new FileInputStream(file);
			subContext.init(reader, fileInputStream, FilePath.getPath(file));
			subContextList.add(subContext);
			fileInputStream.close();
		}
	}

	public void visit(ContextCreator contextCreator) {
        IPropertyReader replacer;
	        try {
	            replacer = (IPropertyReader) contextCreator.create(IContextCreator.REPLACER_BEAN_ID);
	            replacer.load(basePath);
	        }
	        catch (Exception e) {
	            replacer = new NullPropertyReader();
	        }
	}
}
