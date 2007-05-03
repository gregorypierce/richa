package org.richa.runner;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.richa.metadata.ContainerMetaData;
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
	
	public static final String SCRIPTBUFFER = "scriptbuffer" ;
	public static final String CURRENTPAGE = "currentPage" ;
	public static final String CURRENTFORM = "currentForm" ;
	public static final String CURRENTTABPANELNAME = "currentTabPanelName" ;
	public static final String CURRENTBORDERLAYOUTNAME = "currentBorderLayoutName" ;
	public static final String WEBCONTEXT = "webcontext" ;
	public static final String SERVLETCONTEXT = "servletcontext" ;
	
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
		ContainerMetaData currentForm = null;
		ContainerMetaData currentPage = null;
		String currentTabPanelName = null;
		String currentBorderLayoutName = null ;
		
	    JellyContext context = new JellyContext();
	    
	    //Add a buffer to build the script
	    context.setVariable(SCRIPTBUFFER, scriptBuffer);
	    context.setVariable(WEBCONTEXT, webcontext) ;
	    context.setVariable(CURRENTFORM, currentForm) ;
	    context.setVariable(CURRENTPAGE, currentPage) ;
	    context.setVariable(CURRENTTABPANELNAME, currentTabPanelName) ;
	    context.setVariable(CURRENTBORDERLAYOUTNAME, currentBorderLayoutName) ;
	    context.setVariable(SERVLETCONTEXT, servletcontext) ;
	    
	    return context ;
	}
	
	/**
	 * Get the Jelly Script object from the cache
	 * @param context
	 * @return Jelly Script 
	 * @throws Exception
	 */
	private Script getScript(JellyContext context) throws Exception
	{	
		//Check if we have a script object in the cache
	    Script script = RichaPageCache.get(pagename) ;
	    	
 	    //If we don't find  the script
	    if (script == null)
	    {
	    	//Compile the script
	    	script = context.compileScript(getResource(pagename)) ;
	    	
	    	if (isCachingEnabled())
	    	    RichaPageCache.put(pagename, script) ;
	    }	
	    
	    return script ;
	}
    
    protected URL getResource(String pageName) throws MalformedURLException
    {
        return servletcontext.getResource(pagename);
    }
    
    protected boolean isCachingEnabled()
    {
        return true;
    }
}