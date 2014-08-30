package com.jpattern.gwt.server.rest;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.jpattern.gwt.shared.IWebResultResultObject;
import com.jpattern.gwt.shared.IWebResultString;
import com.jpattern.gwt.shared.ResultObject;
import com.jpattern.gwt.shared.WebResultResultObject;
import com.jpattern.gwt.shared.WebResultString;

/**
 * The server side implementation of the rest service.
 */
@Path("echo")
public class EchoServiceImpl {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public IWebResultResultObject echoPost(ResultObject data) throws IllegalArgumentException {
		System.out.println("POST RestService Called " + new Date().getTime());

		ResultObject result = new ResultObject();
		for (int i=0; i<data.getMessages().size(); i++) {
			result.getMessages().add( data.getMessages().get(i) );
		}
		
		result.setValid(data.isValid());
		result.setName("POST");
		
		return new WebResultResultObject(result);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public IWebResultResultObject echoPut(ResultObject data) throws IllegalArgumentException {
		System.out.println("PUT RestService Called " + new Date().getTime());

		ResultObject result = new ResultObject();
		for (int i=0; i<data.getMessages().size(); i++) {
			result.getMessages().add( data.getMessages().get(i) );
		}
		
		result.setValid(data.isValid());
		result.setName("PUT");
		
		return new WebResultResultObject(result);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public IWebResultResultObject echoGet(@DefaultValue("") @QueryParam("input") String input, @DefaultValue("1") @QueryParam("repeat") String repeat) throws IllegalArgumentException {
		System.out.println("GET RestService Called " + new Date().getTime());

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		int reapeatTimes = Integer.valueOf(repeat);
		

		ResultObject result = new ResultObject();
		for (int i=0; i<reapeatTimes; i++) {
			result.getMessages().add( i + input);
		}
		
		result.setValid(true);
		result.setName("GET");
		
		return new WebResultResultObject(result);
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public IWebResultResultObject echoDelete(@DefaultValue("") @QueryParam("input") String input, @DefaultValue("1") @QueryParam("repeat") String repeat) throws IllegalArgumentException {
		System.out.println("DELETE RestService Called " + new Date().getTime());

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		int reapeatTimes = Integer.valueOf(repeat);
		

		ResultObject result = new ResultObject();
		for (int i=0; i<reapeatTimes; i++) {
			result.getMessages().add( i + input);
		}
		
		result.setValid(true);
		result.setName("DELETE");
		
		return new WebResultResultObject(result);
	}
	
	@GET
	@Path("wait500ms")
	@Produces(MediaType.APPLICATION_JSON)
	public IWebResultString sleep(@DefaultValue("0") @QueryParam("sleepFor") int sleepFor) throws InterruptedException {
		System.out.println("GET sleep RestService Called " + new Date().getTime() + " - sleep for " + sleepFor + "ms");
//		int waitFor = 500 + new Random().nextInt(250);
		Thread.sleep(sleepFor);
		return new WebResultString("Slept for " + sleepFor + "ms");
	}
	
	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
