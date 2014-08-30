package com.jpattern.service.log.mock;

import com.jpattern.logger.ILogger;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class MockApplication {

    private ILogger _message;

    public MockApplication(ILogger aMessage) {
        _message = aMessage;
    }

    public void faiQualcheCosaInInfo() {
        _message.info("" , "fired ho fatto qualche cosa IN INFO");

    }

    public void faiQualcheCosaInErrore() {
        _message.error("" , " fired ho fatto qualche cosa IN ERROR", new Exception("nuova eccezione"));

    }

    public void faiQualcheCosaInErroreSenzaEccezione() {
        _message.error("" , " fired ho fatto qualche cosa IN ERROR SENZA ECCEZIONE ");
    }

    public void faiQualcheCosaInDebug() {
        _message.debug("" , " fired ho fatto qualche cosa IN DEBUG ");

    }
    
    public void faiQualcheCosaInTrace() {
        _message.trace(""," azione compiuta in trace");

    }

}
