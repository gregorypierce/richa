package org.richa.runner;

import java.net.URL;

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
	public static final String SCRIPTBUFFER = "scriptbuffer" ;
	public static final String CURRENTFORMNAME = "currentFormName" ;
	public static final String CURRENTTABNAME = "currentTabName" ;
	public static final String WEBCONTEXT = "webcontext" ;
	
	public static AppendingStringBuffer runPage(URL pageurl,String webcontext) throws Exception
	{
		StringBufferWriter output = new StringBufferWriter() ;
		AppendingStringBuffer scriptBuffer = new AppendingStringBuffer() ;
		String currentFormName = null;
		String currentTabName = null;
		
	    JellyContext context = new JellyContext();
	    
	    //Add a buffer to build the script
	    context.setVariable(SCRIPTBUFFER, scriptBuffer);
	    context.setVariable(WEBCONTEXT, webcontext) ;
	    context.setVariable(CURRENTFORMNAME, currentFormName) ;
	    context.setVariable(CURRENTTABNAME, currentTabName) ;
	    
	    //Create the output
	    XMLOutput xmlOutput = XMLOutput.createXMLOutput(output) ;
	    
	    //Compile the script
	    Script script = context.compileScript(pageurl) ;
	    
	    //Run the script
	    script.run(context, xmlOutput);
	    
	    //Flush the output
	    xmlOutput.flush();
	    
	    //Return the stringbuffer
	    return output.getStringBuffer() ;
	}
	
	
}
