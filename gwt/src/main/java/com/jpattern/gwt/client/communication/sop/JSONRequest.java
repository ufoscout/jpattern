package com.jpattern.gwt.client.communication.sop;

/**
 * 
 * @author Francesco Cina'
 *
 * 13/mag/2011
 * 
 * This class is intended to resolve the Same Origin Problem.
 * Code found on internet, source site:
 * http://doctordooley.blogspot.com/2009/07/gwt-sop-workaround.html
 *  
 */
public class JSONRequest {
    public static void get(String url, JSONRequestHandler handler) {
        String callbackName = "JSONCallback" + handler.hashCode();
        get(url + callbackName, callbackName, handler);
    }

    public static void get(String url, String callbackName,
            JSONRequestHandler handler) {
        createCallbackFunction(handler, callbackName);
        addScript(url);
    }

    public static native void addScript(String url) /*-{
                                                    var scr = document.createElement("script");
                                                    scr.setAttribute("language", "JavaScript");
                                                    scr.setAttribute("src", url);
                                                    document.getElementsByTagName("body")[0].appendChild(scr);
                                                    }-*/;

    private native static void createCallbackFunction(JSONRequestHandler obj,
            String callbackName)/*-{
                                tmpcallback = function(j) {
                                obj.@com.jpattern.gwt.client.communication.sop.JSONRequestHandler::onRequestComplete(Lcom/google/gwt/core/client/JavaScriptObject;)(j);
                                };
                                eval( "window." + callbackName + "=tmpcallback" );
                                }-*/;
}