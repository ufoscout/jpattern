package com.jpattern.jobexecutor.socket;

import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Francesco Cina'
 *
 * 03/apr/2010
 */
public class GenericConsoleComunicationClientStrategy implements ICommunicationClientStrategy {

	private static final long serialVersionUID = 1L;
	private String readMessage = "";
	private boolean firstRead = true;
	
	public GenericConsoleComunicationClientStrategy(String firstMessage) {
		readMessage = firstMessage;
	}
	
	public String read() throws IOException {
		if (firstRead){
			firstRead = false;
			return readMessage;
		}
		System.out.print("shell:\\ ");
		Scanner in = new Scanner ( System.in ) ;
        String text = in.nextLine();
		return text;
	}

	public void write(String message) {
		System.out.println(message);
	}
	
}
