package org.richa.tags.extjs.helper ;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.tags.extjs.BaseExtJSTag;
import org.xml.sax.SAXException;

/**
 * This class represents a Page tag. There should be only page tag in one page
 * @author ram
 *
 */
public class Page extends BaseExtJSTag
{
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		//Initialize the script
		startScript() ;
	}
	
	/**
	 * Generate html and js after the body is processed
	 */
	protected void afterBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		//End the script
		endScript() ;
		
		//Write to the output stream
		output.write(scriptBuffer.toString()) ;
	}
	
	private void startScript()
	{
		scriptBuffer.appendln("<script>") ;
		scriptBuffer.appendln("Ext.onReady(function(){") ;
	}
	
	private void endScript()
	{	
		scriptBuffer.appendln("})") ;
		scriptBuffer.appendln("</script>");
	}
}

