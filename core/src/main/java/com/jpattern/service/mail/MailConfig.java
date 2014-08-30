package com.jpattern.service.mail;
/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 01/set/09 20:30:37
 *
 * @version $Id$
 */
public class MailConfig implements IMailConfig {

 
    private static final long serialVersionUID = 1L;

    
    private String stmphost;
    
    private String stmpport;
    
    private String username;
    
    private String password;
    
    private boolean requiredAuthentication;

    public String getStmphost() {
        return stmphost;
    }

    public void setStmphost(String stmphost) {
        this.stmphost = stmphost;
    }

    public String getStmpport() {
        return stmpport;
    }

    public void setStmpport(String stmpport) {
        this.stmpport = stmpport;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRequiredAuthentication() {
		return requiredAuthentication;
	}

	public void setRequiredAuthentication(boolean requiredAuthentication) {
		this.requiredAuthentication = requiredAuthentication;
	}
    
}
