package com.jpattern;

import java.io.File;
import java.io.FileInputStream;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;


/**
 * 
 * @author Francesco Cina'
 *
 * 15/nov/2010
 */
public class EmbeddedJettyServer {

    private static EmbeddedJettyServer jettyServer;
    private Server SERVER;
    private String CONTEXT = "/context";
    private int SERVER_PORT = 0; // serverPort = 0 means "assign arbitrarily port number"
    private int ACTUAL_PORT; // This field is initialized with the real port number used by jetty
    

	private EmbeddedJettyServer() throws Exception {
		configureJetty(SERVER_PORT);
    }

	public static EmbeddedJettyServer instance() throws Exception {
		if (jettyServer==null) {
			jettyServer = new EmbeddedJettyServer();
		}
		return jettyServer;
	}
	
	
	private void configureJetty(int serverPort) throws Exception {
        SERVER = new Server(serverPort);
        
        XmlConfiguration configuration = new XmlConfiguration(new FileInputStream("src/test/jetty7/jetty-env.xml"));
        configuration.configure(SERVER);
        
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath(CONTEXT);
		webAppContext.setBaseResource(new ResourceCollection("src/main/webapp, src/test/webapp"));
//        webAppContext.setBaseResource(new ResourceCollection("src/test/webapp"));
        webAppContext.setTempDirectory( new File("target/jetty-temp"));
        webAppContext.setDefaultsDescriptor("src/test/jetty7/webdefault.xml");

        SERVER.setHandler( webAppContext );
        SERVER.start();

        // getLocalPort returns the port that was actually assigned
        ACTUAL_PORT = SERVER.getConnectors()[0].getLocalPort();
        System.out.println("Jetty server started on port: " + ACTUAL_PORT);

	}
	
	public String getBaseWebUrl() {
		return "http://localhost:" + ACTUAL_PORT + CONTEXT;		
	}
    
}
