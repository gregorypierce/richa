package org.richa.tags.extjs.form;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.xml.sax.SAXException;

/**
 * This class represents a ComboBox tag
 * @author ram
 *
 */
public class ComboBox extends BaseControlTag
{
	/**
	 * Return the javascript object name to generate
	 */
	protected String getObjectName()
	{
		return ("Ext.form.ComboBox") ;
	}
	
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		String name = getName() ;
		if (isEmpty(name))
			throw new JellyTagException("name is a required parameter for this tag") ;
		
		//Serialize the creation of the tag
		serialize(true) ;
		
		//Get the name of the store
		String store = (String)getAttribute(STORE) ;
		if (isEmpty(store))
			throw new JellyTagException("store is a required parameter for this tag") ;
	}
}