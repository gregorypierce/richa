package org.richa.runner;

import java.net.URL;
import java.util.Hashtable;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.richa.util.AppendingStringBuffer;
import org.richa.util.StringBufferWriter;


/**
 * Use Jelly to execute a Simpli template
 * @author ram
 */
public class RichaRunner
{
	//Cache for the script object
	private static Hashtable<String,Script> scriptCache = new Hashtable<String,Script>() ;
	
	public static final String SCRIPTBUFFER = "scriptbuffer" ;
	public static final String CURRENTFORMNAME = "currentFormName" ;
	public static final String CURRENTTABPANELNAME = "currentTabPanelName" ;
	public static final String CURRENTBORDERLAYOUTNAME = "currentBorderLayoutName" ;
	public static final String WEBCONTEXT = "webcontext" ;
	
	public static AppendingStringBuffer runPage(URL pageurl,String webcontext) throws Exception
	{
		StringBufferWriter output = new StringBufferWriter() ;
		AppendingStringBuffer scriptBuffer = new AppendingStringBuffer() ;
		String currentFormName = null;
		String currentTabPanelName = null;
		String currentBorderLayoutName = null ;
		
	    JellyContext context = new JellyContext();
	    
	    //Add a buffer to build the script
	    context.setVariable(SCRIPTBUFFER, scriptBuffer);
	    context.setVariable(WEBCONTEXT, webcontext) ;
	    context.setVariable(CURRENTFORMNAME, currentFormName) ;
	    context.setVariable(CURRENTTABPANELNAME, currentTabPanelName) ;
	    context.setVariable(CURRENTBORDERLAYOUTNAME, currentBorderLayoutName) ;
	    
	    //Create the output
	    XMLOutput xmlOutput = XMLOutput.createXMLOutput(output) ;
	
	    //Check if we have a script object in the cache
	    Script script = scriptCache.get(pageurl.getPath()) ;
	   
 	    //If we don't find the script
	    if (script == null)
	    {
	    	//Compile the script
	    	script = context.compileScript(pageurl) ;
	    	
	    	//Cache
	    	//scriptCache.put(pageurl.getPath(), script) ;
	    }
	    
	    //Run the script
	    script.run(context, xmlOutput);
	    
	    //Flush the output
	    xmlOutput.flush();
	    
	    //Return the stringbuffer
	    return output.getStringBuffer() ;
	}
}