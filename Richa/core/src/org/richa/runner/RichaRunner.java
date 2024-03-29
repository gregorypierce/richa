package org.richa.runner;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.servlet.ServletContext;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.richa.config.Application;
import org.richa.util.AppendingStringBuffer;
import org.richa.util.StringBufferWriter;


/**
 * Use Jelly to execute a Richa Pages
 * @author ram
 */
public class RichaRunner
{
	//Root path of the contex
	private static String rootpath ;
	
	public static final String SCRIPTBUFFER = "!scriptbuffer!" ;
	public static final String EVENTBUFFER = "!eventbuffer!" ;
	public static final String CURRENTFORM = "!currentform!" ;
	public static final String CURRENTTABPANELNAME = "!currenttabpanelname!" ;
	public static final String CURRENTBORDERLAYOUTNAME = "!currentborderlayoutname!" ;
	public static final String WEBCONTEXT = "!webcontext!" ;
	public static final String LISTENERSTACK = "!listenertack!" ;
	public static final String SERVLETCONTEXT = "!servletcontext!" ;
	public static final String BINDINGCONTEXT = "!bindingcontext!" ;
	
	//Context name
	private String webcontext ;
	
	//Servlet Context
	private ServletContext servletcontext ;
	
	//Page Name
	private String pagename ;
	
	/**
	 * Set the root path for the context
	 * @param path
	 */
	public static void setRootPath(String path)
	{
		rootpath = path ;
	}
	
	/**
	 * Get the root path for the context
	 * @param path
	 */
	public static String getRootPath()
	{
		return (rootpath) ;
	}
	
	/**
	 * Set the web context
	 * @param context
	 */
	public void setWebContext(String webcontext)
	{
		this.webcontext = webcontext ;
	}
	
	/**
	 * Set the servlet context
	 * @param servletcontext
	 */
	public void setServletContext(ServletContext servletcontext)
	{
		this.servletcontext = servletcontext ;
	}
	
	/**
	 * Set the page name
	 * @param pagename
	 */
	public void setPageName(String pagename)
	{
		this.pagename = pagename ;
	}
	
	/**
	 * Run the page
	 * @param pageurl - URL representing a page
	 * @return return a buffer with the markup
	 * @throws Exception
	 */
	public AppendingStringBuffer runPage() throws Exception
	{
		//Output Buffer
		StringBufferWriter output = new StringBufferWriter() ;
		
		//Initialize and create a context
	    JellyContext context = initContext() ;
	    
	    //Create the output
	    XMLOutput xmlOutput = XMLOutput.createXMLOutput(output) ;
	
	    //Check if we have a script object in the cache
	    Script script = getScript(context) ;
	    if (script == null)
	    	return null ;
	    
	    //Run the script
	    script.run(context, xmlOutput);
	    
	    //Flush the output
	    xmlOutput.flush();
	    
	    //Return the stringbuffer
	    return output.getStringBuffer() ;
	}
	
	/**
	 * Initalize a Jelly Context
	 * @return
	 */
	protected JellyContext initContext()
	{
		AppendingStringBuffer scriptBuffer = new AppendingStringBuffer() ;
		AppendingStringBuffer eventBuffer = new AppendingStringBuffer() ;
		String currentTabPanelName = null;
		String currentBorderLayoutName = null ;
		String currentFormName = null ;
		Map<String,Object> bindingcontext = new HashMap<String,Object>() ;
		Stack<String> listenerStack = new Stack<String>() ;
		
	    JellyContext context = new JellyContext();
	    
	    //Add a buffer to build the script
	    context.setVariable(SCRIPTBUFFER, scriptBuffer);
	    context.setVariable(EVENTBUFFER, eventBuffer);
	    context.setVariable(WEBCONTEXT, webcontext) ;
	    context.setVariable(CURRENTFORM, currentFormName) ;
	    context.setVariable(CURRENTTABPANELNAME, currentTabPanelName) ;
	    context.setVariable(CURRENTBORDERLAYOUTNAME, currentBorderLayoutName) ;
	    context.setVariable(SERVLETCONTEXT, servletcontext) ;
	    context.setVariable(LISTENERSTACK, listenerStack) ;
	    context.setVariable(BINDINGCONTEXT, bindingcontext) ;
	    
	    return context ;
	}
	
	/**
	 * Get the Jelly Script object from the cache
	 */
	private Script getScript(JellyContext context) throws Exception
	{	
		//Check if we have a script object in the cache
	    Script script = RichaPageCache.get(pagename) ;
	    	
 	    //If we don't find  the script
	    if (script == null)
	    {	  
	    	//Compile the script
	    	URL url = getResource(pagename) ;
	    	if (url == null)
	    		return null ;
	    	
	    	script = context.compileScript(url) ;
	    	
	    	if (isCachingEnabled())
	    	    RichaPageCache.put(pagename, script) ;
	    }	
	    
	    return script ;
	}
    
    protected URL getResource(String pageName) throws MalformedURLException
    {
        return (servletcontext.getResource(pagename));        
    }
    
    protected boolean isCachingEnabled()
    {
        String caching = (String) Application.getInstance().get("iscachingenabled") ;
        if (caching.equalsIgnoreCase("true"))
        	return true ;
        else
        	return false ;
    }
}