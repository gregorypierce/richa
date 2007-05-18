package org.richa.tags.extjs.layouts;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.tags.extjs.BaseExtJSTag;
import org.xml.sax.SAXException;

/**
 * This class represents a NumberField tag
 * @author ram
 *
 */
public class ContentPanel extends BaseExtJSTag
{
	/**
	 * Return the javascript object name to generate
	 */
	protected String getObjectName()
	{
		return ("Ext.ContentPanel") ;
	}
	
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		String region = (String) getAttribute(REGION) ;
		if (isEmpty(region))
			throw new JellyTagException("region is a required parameter for this tag") ;
		
		String element = (String) getAttribute(ELEMENT) ;
		if (isEmpty(element))
			throw new JellyTagException("element is a required parameter for this tag") ;
		
		//Ensure that there is a layout tag active
		if (isEmpty(getCurrentBorderLayoutName()))
			throw new JellyTagException("contentpanel tag can only be enclosed inside a borderlayout tag") ;
		
		//Serialize the creation of the tag
		serialize(region,element) ;
	}
	
	/**
	 * Generate the create of the layout
	 * @param region
	 * @param element
	 */
	protected void serialize(String region, String element)
	{
		scriptBuffer.appendln("    " + getCurrentBorderLayoutName() + ".add(\"" + region + "\", new " + getObjectName() + "(\"" + element + "\", {" ) ;
		
		serializeAttributes() ;
		
		scriptBuffer.appendln("    }));") ;
	}
}

