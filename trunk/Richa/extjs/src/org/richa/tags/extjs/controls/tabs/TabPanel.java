package org.richa.tags.extjs.controls.tabs;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.tags.extjs.BaseExtJSTag;
import org.xml.sax.SAXException;

/**
 * This class represents a TabPanel tag
 * @author ram
 *
 */
public class TabPanel extends BaseExtJSTag
{
	/**
	 * Return the javascript object name to generate
	 */
	protected String getObjectName()
	{
		return ("Ext.TabPanel") ;
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
		
		//Is there another tab tag open
		if (!isEmpty(getCurrentTabPanelName()))
			throw new JellyTagException("TabPanels cannot be embedded") ;
		
		//Generate th div for the tab panel
		output.write("<div id=\"" + "tab-" + name + "-id" + "\">\n") ;
		
		//Generate a tab Panel creation
		serializeCreation() ;
		
		//Set this tab panel as the current tab panel
		setCurrentTabPanelName(name) ;
	}
	
	/**
	 * Generate html and js after the body is processed
	 */
	protected void afterBody(final XMLOutput output) throws JellyTagException, SAXException
	{	
		output.write("</div>\n") ;
		
	    //Clear the current tab panel name
	    clearCurrentTabPanelName() ;
	}   
	
	//Create the tab panel
	protected void serializeCreation()
	{
		scriptBuffer.appendln("    var " + getName() + " = new " + getObjectName() + "(\"tab-" + getName() + "-id\", {");
		
		//Serialize all the attributes
		serializeAttributes() ;
		
		scriptBuffer.appendln("    });") ;
	}
}
