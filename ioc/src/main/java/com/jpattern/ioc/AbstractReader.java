package com.jpattern.ioc;

import java.io.InputStream;

import com.jpattern.ioc.xml.Context;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 24/ott/08 11:23:52
 * @updated Francesco Cina - 27/nov/10
 * @version $Id: $
 */
public abstract class AbstractReader {

        public abstract void read(Context context, InputStream aInputStream);
        
}
