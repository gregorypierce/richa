package org.richa.tags.extjs.layouts ;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.tags.extjs.BaseExtJSTag;
import org.xml.sax.SAXException;

/**
 * This class represents a BorderLayout tag
 * @author ram
 *
 */
public class BorderLayout extends BaseExtJSTag
{
	/**
	 * Return the javascript object name to generate
	 */
	protected String getObjectName()
	{
		return ("Ext.BorderLayout") ;
	}
	
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		//Does it have a valid name
		String name = getName() ;
		if (isEmpty(name))
			throw new JellyTagException("name is a required parameter for this tag") ;
		
		//Is there another border layout open
		if (!isEmpty(getCurrentBorderLayoutName()))
			throw new JellyTagException("Border layouts cannot be embedded") ;
		
		//Get the element in which we need to create the layout
		String element = (String) getAttribute("element") ;
		if (isEmpty(element))
			throw new JellyTagException("element is a required parameter for this tag") ;
		
		if (!element.equals("document.body"))
			element = "Ext.get(" + element + ")" ;
		
		//Generate a border layout creation
		scriptBuffer.appendln("    " + getName() + " = new " + getObjectName() + "(" + element + ");") ;
		
		//Set the current layout name as current
		setCurrentBorderLayoutName(name) ;
	}
	
	/**
	 * Generate html and js after the body is processed
	 */
	protected void afterBody(final XMLOutput output) throws JellyTagException, SAXException
	{	
	    //Clear the current tab panel name
	    clearCurrentBorderLayoutName() ;
	}   
}

