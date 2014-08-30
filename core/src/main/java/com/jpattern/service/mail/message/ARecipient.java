package com.jpattern.service.mail.message;


/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 01/set/09 20:53:17
 *
 * @version $Id$
 */
public abstract class ARecipient implements IRecipient {

        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String _recipient;

        public ARecipient (String aRecipient) {
                _recipient = aRecipient;
        }
        
        public final String name() {
            return _recipient;
        }
        
        public int hashCode() {
            return _recipient.hashCode();
        }

        public boolean equals(ARecipient obj) {
        	if( obj == null) return false;
            return _recipient.equals(obj._recipient);
        }        
}
