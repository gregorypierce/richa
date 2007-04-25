package org.richa.tags.extjs.layouts;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.tags.extjs.BaseExtJSTag;
import org.xml.sax.SAXException;

/**
 * This class represents a ContentPanel tag
 * @author ram
 *
 */
public class NestedLayoutPanel extends BaseExtJSTag
{
	/**
	 * Return the javascript object name to generate
	 */
	protected String getObjectName()
	{
		return ("Ext.NestedLayoutPanel") ;
	}
	
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		String region = getAttribute("region") ;
		if (isEmpty(region))
			throw new JellyTagException("region is a required parameter for this tag") ;
		
		String borderlayout = getAttribute("borderLayout") ;
		if (isEmpty(borderlayout))
			throw new JellyTagException("borderLayout is a required parameter for this tag") ;
		
		//Ensure that there is a layout tag active
		if (isEmpty(getCurrentBorderLayoutName()))
			throw new JellyTagException("nestedlayoutpanel tag can only be enclosed inside a borderlayout tag") ;
		
		//Serialize the creation of the tag
		serializeCreation(region,borderlayout) ;
	}
	
	
	protected void serializeCreation(String region, String borderlayout)
	{
		scriptBuffer.appendln("    " + getCurrentBorderLayoutName() + ".add(\"" + region + "\", new " + getObjectName() + "(\"" + borderlayout + "\", {" ) ;
		
		serializeAttributes() ;
		
		scriptBuffer.appendln("    }));") ;
	}
}

