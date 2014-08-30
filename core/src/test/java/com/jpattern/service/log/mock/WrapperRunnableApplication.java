package com.jpattern.service.log.mock;

import com.jpattern.logger.ILogger;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class WrapperRunnableApplication implements Runnable {
    
    
    private MockApplication _mockApplication;
    private boolean _start = true;

    public WrapperRunnableApplication(ILogger aMessage) {
     _mockApplication = new MockApplication(aMessage);
    }

    public void run() {
        
        while (_start) {
            
            try {
                Thread.sleep( 100 );
                _mockApplication.faiQualcheCosaInInfo();
                Thread.sleep( 100 );
                _mockApplication.faiQualcheCosaInDebug();
                Thread.sleep( 100 );
                _mockApplication.faiQualcheCosaInErrore();
                Thread.sleep( 100 );
                _mockApplication.faiQualcheCosaInErroreSenzaEccezione();                
                
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
        
    }
    
    public void stop() {
        _start = false;
    }

}
