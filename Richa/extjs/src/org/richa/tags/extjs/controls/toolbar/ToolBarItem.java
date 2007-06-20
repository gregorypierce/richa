package org.richa.tags.extjs.controls.toolbar;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.tags.extjs.BaseExtJSTag;
import org.xml.sax.SAXException;

/**
 * This class represents a ToolBarItem tag
 * @author ram
 *
 */
public abstract class ToolBarItem extends BaseExtJSTag
{	
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		//Does it have a valid name
		String name = getName() ;
		if (isEmpty(name))
			throw new JellyTagException("name is a required parameter for this tag") ;
		
		//Check if there a menu active 
		String toolbar = getCurrentToolBarName() ;
		if (toolbar != null)
			throw new JellyTagException("toolbar items must be embedded inside toolbar tags") ;
		
		//Generate menu creation
		serialize(false) ;
		
		//Append the menu item to the menu
		scriptBuffer.append("    " + toolbar + ".addItem(" + name + ");") ;
		
	}   
}
