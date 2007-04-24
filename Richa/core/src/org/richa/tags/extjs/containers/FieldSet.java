package org.richa.tags.extjs.containers;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.xml.sax.SAXException;

/**
 * This class represents a FieldSet tag
 * @author ram
 *
 */
public class FieldSet extends BaseContainerTag
{
	/**
	 * Return the javascript object name to generate
	 */
	protected String getObjectName()
	{
		return ("fieldset") ;
	}
	
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		//Serialize the creation of the tag
		serializeChildCreation() ;
	}	
	
	/**
	 * Generate html and js after the body is processed
	 */
	protected void afterBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		//Close the container
		scriptBuffer.appendln("    " + getCurrentFormName()+ ".end();"); 
	}
}

