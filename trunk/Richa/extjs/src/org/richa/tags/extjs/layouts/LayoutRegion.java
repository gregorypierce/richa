package org.richa.tags.extjs.layouts;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.tags.extjs.BaseExtJSTag;
import org.xml.sax.SAXException;

/**
 * This class represents a Button tag
 * @author ram
 *
 */
public class LayoutRegion extends BaseExtJSTag
{
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		String region = getAttribute("region") ;
		if (isEmpty(region))
			throw new JellyTagException("region is a required parameter for this tag") ;
		
		//Ensure that there is a layout tag active
		if (isEmpty(getCurrentBorderLayoutName()))
			throw new JellyTagException("layoutregion tag can only be enclosed inside a borderlayout tag") ;
		
		//Serialize the creation of the tag 
		serializeCreation(region) ;	
	}
	
	
	/**
	 * Serialize the region object
	 */
	protected void serializeCreation(String region)
	{
		scriptBuffer.appendln("    " + getCurrentBorderLayoutName() + ".addRegion(\"" + region + "\", {");
		
		//Serialize all the attributes
		serializeAttributes() ;
		
		scriptBuffer.appendln("    });") ;
	}
}

