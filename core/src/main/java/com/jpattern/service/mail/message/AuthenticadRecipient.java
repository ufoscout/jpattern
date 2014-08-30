package com.jpattern.service.mail.message;


public class AuthenticadRecipient implements IRecipient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IRecipient _recipient;
	private String _recipientpassword;
	public AuthenticadRecipient(IRecipient recipient,String recipientPassword) {
		_recipient = recipient;
		_recipientpassword =recipientPassword;
		
	}
	public String name() {
		return _recipient.name();
	} 
	public String password(){
		return _recipientpassword;
	}
	
}
