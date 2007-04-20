package org.richa.tags.extjs.form;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.xml.sax.SAXException;

/**
 * This class represents a TextArea tag
 * @author ram
 *
 */
public class TextArea extends BaseControlTag
{
	/**
	 * Return the javascript object name to generate
	 */
	protected String getObjectName()
	{
		return ("Ext.form.TextArea") ;
	}
	
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		String name = getName() ;
		if (isEmpty(name))
			throw new JellyTagException("Name is a required parameter for this tag") ;
		
		//Serialize the creation of the tag
		serializeCreation() ;	
	}
}

