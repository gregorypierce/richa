package org.richa.tags.extjs.controls.toolbar;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.tags.extjs.BaseExtJSTag;
import org.xml.sax.SAXException;

/**
 * This class represents a ToolBar tag
 * @author ram
 *
 */
public class Toolbar extends BaseExtJSTag
{
	/**
	 * Return the javascript object name to generate
	 */
	protected String getObjectName()
	{
		return ("Ext.Toolbar") ;
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
		
		//Generate menu creation
		serialize(false) ;
		
		//Set the toolbar name
		setCurrentToolBarName(name) ;
	}
	
	/**
	 * Generate html and js after the body is processed
	 */
	protected void afterBody(final XMLOutput output) throws JellyTagException, SAXException
	{	
	    //Clear the toolbar name
	    clearCurrentToolBarName() ;
	} 
}
