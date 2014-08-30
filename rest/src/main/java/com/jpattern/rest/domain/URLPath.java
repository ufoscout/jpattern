package com.jpattern.rest.domain;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/mag/2011
 */
public class URLPath implements Serializable {

	private static final long serialVersionUID = 1L;

	// Sample URL:                 http://hostname.com:80/mywebapp/servlet/MyServlet/a/b;c=123?d=789
	
    private String scheme = "";       // http
    private String serverName = "";   // hostname.com
    private int serverPort = 0;       // 80
    private String contextPath = "";  // /mywebapp
    private String servletPath = "";  // /servlet/MyServlet
    private String pathInfo = "";     // /a/b;c=123
    private String queryString = "";  // d=789
    
    public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public int getServerPort() {
		return serverPort;
	}
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	public String getServletPath() {
		return servletPath;
	}
	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}
	public String getPathInfo() {
		return pathInfo;
	}
	public void setPathInfo(String pathInfo) {
		this.pathInfo = pathInfo;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	
	public String getCompleteURL() {
		String url = scheme+"://"+serverName+":"+serverPort+contextPath+servletPath+pathInfo;
	    if (queryString.length()>0) {
	        url += "?"+queryString;
	    }
	    return url;
	}
}
