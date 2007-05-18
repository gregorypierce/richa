package org.richa.tags.extjs.eyecandy;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.tags.extjs.BaseExtJSTag;
import org.xml.sax.SAXException;

/**
 * This class represents a FormWrapper tag
 * @author ram
 *
 */
public class FormWrapper extends BaseExtJSTag
{	
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		String width = (String)getAttribute(WIDTH) ;
		if (isEmpty(width))	
			throw new JellyTagException("width is a required parameter for this tag") ;
		
		
		String title = (String)getAttribute(TITLE) ;
		if (isEmpty(title))	
			throw new JellyTagException("title is a required parameter for this tag") ;
		
		
		output.write("<div style=\"width:" + width + ";\">\n") ;
	    output.write("<div class=\"x-box-tl\"><div class=\"x-box-tr\"><div class=\"x-box-tc\"></div></div></div>\n") ;
	    output.write("<div class=\"x-box-ml\"><div class=\"x-box-mr\"><div class=\"x-box-mc\">\n") ;
	    output.write("<h3>" + title + "</h3>\n") ;
	}
	
	/**
	 * Generate html and js after the body is processed
	 */
	protected void afterBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		output.write("</div></div></div>") ;
	    output.write("<div class=\"x-box-bl\"><div class=\"x-box-br\"><div class=\"x-box-bc\"></div></div></div></div>") ;
	}
}

