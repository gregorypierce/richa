package org.richa.tags.extjs.containers;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.xml.sax.SAXException;

/**
 * This class represents a Form tag
 * @author ram
 *
 */
public class Form extends BaseContainerTag
{
	/**
	 * Return the javascript object name to generate
	 */
	protected String getObjectName()
	{
		return ("Ext.form.Form") ;
	}
	
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		String name = getName() ;
		if (isEmpty(name))
			throw new JellyTagException("name is a required parameter for this tag") ;
		
		//Generate a div for the form place holder
		output.write("<div id=\"" + "form-" + name + "-id" + "\"></div>") ;
		
		//Serialize the creation of the tag
		serializeCreation() ;
		
		//Set the current form name
		setCurrentFormName(name) ;
	}
	
	/**
	 * Generate html and js after the body is processed
	 */
	protected void afterBody(final XMLOutput output) throws JellyTagException, SAXException
	{	
		//Close the container
		scriptBuffer.appendln("    " + getName()+ ".end();"); 
	    scriptBuffer.appendln("    " + getName() + ".render('" + "form-" + getName() + "-id" + "');");
	    
	    //Clear the current form name
	    clearCurrentFormName() ;
	}   
}