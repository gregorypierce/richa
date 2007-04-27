package org.richa.tags.extjs.controls.tab;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.tags.extjs.BaseExtJSTag;
import org.xml.sax.SAXException;

/**
 * This class represents a TabPanel Item tag
 * @author ram
 *
 */
public class TabPanelItem extends BaseExtJSTag
{
	/**
	 * Return the javascript object name to generate
	 */
	protected String getObjectName()
	{
		return ("Ext.TabPanelItem") ;
	}
	
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		//Does it have valid name
		String name = getName() ;
		if (isEmpty(name))
			throw new JellyTagException("name is a required parameter for this tag") ;
		
		String text  = getAttribute("text") ;
		
		//Is an active tab panel name
		if (isEmpty(getCurrentTabPanelName()))
			throw new JellyTagException("There is no tab pane active panel tag open at this time") ;
		
		//Is there a valid tab label
		if (isEmpty(text))
			throw new JellyTagException("text is a required parameter for this tag") ;
		
		//Generate th div for the tab panel item
		output.write("<div id=\"" + "tabitem-" + name + "-id" + "\">\n") ;
		
		//Generate a tab Panel Item creation
		scriptBuffer.appendln("    " + "var " + name + " = " + getCurrentTabPanelName() + ".addTab(\"tabitem-" + name + "-id\",\"" + text + "\");") ;
		
		//Does this tab need to be active
		String active = getAttribute("active") ;
		if (!isEmpty(active))
			scriptBuffer.appendln("    " + getCurrentTabPanelName() + ".activate(\"tabitem-" + name + "-id\")") ;
		
		//Does this tab need to be hidden
		String hidden = getAttribute("hidden") ;
		if (!isEmpty(hidden))
			scriptBuffer.appendln("    " + getCurrentTabPanelName() + ".hideTab(\"tabitem-" + name + "-id\")") ;
		
		//Does this tab need to be disabled
		String disable = getAttribute("disable") ;
		if (!isEmpty(disable))
			scriptBuffer.appendln("    " + getCurrentTabPanelName() + ".disableTab(\"tabitem-" + name + "-id\")") ;
		
		//If the tab item has a url where the tab content will be loaded from
		String contentURL = getAttribute("contentURL") ;
		if (!isEmpty(contentURL))
		{
			scriptBuffer.appendln("    Ext.get(\"tabitem-" + name + "-id\").load({") ;
			scriptBuffer.appendln("    url:'" + webContext +  contentURL + "',") ;
			scriptBuffer.appendln("    scripts:true,") ;
			scriptBuffer.appendln("    text:'Loading tab pane...'") ;
			scriptBuffer.appendln("    });") ;
		}
	}
	
	/**
	 * Generate html and js after the body is processed
	 */
	protected void afterBody(final XMLOutput output) throws JellyTagException, SAXException
	{	
		output.write("</div>\n") ;
	}  
}