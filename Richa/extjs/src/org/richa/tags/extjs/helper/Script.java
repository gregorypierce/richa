package org.richa.tags.extjs.helper;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.tags.extjs.BaseExtJSTag;
import org.xml.sax.SAXException;

/**
 * This class represents a Script tag. This tag is used to include all the
 * scripts required by the framework. Include this in the head of the HTML
 * @author ram 
 *
 */
public class Script extends BaseExtJSTag
{
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		output.write("<script type=\"text/javascript\" src=\"" + webContext + "/resources/js/yui-utilities.js\"></script>\n") ;
		output.write("<script type=\"text/javascript\" src=\"" + webContext + "/resources/js/ext-yui-adapter.js\"></script>\n") ; 
	    output.write("<script type=\"text/javascript\" src=\"" + webContext + "/resources/js/ext-all.js\"></script>\n") ;	
	    output.write("<script type=\"text/javascript\" src=\"" + webContext + "/resources/js/richa-core.js\"></script>\n") ;
	    output.write("<script type=\"text/javascript\" src=\"" + webContext + "/resources/js/richa-ext.js\"></script>\n") ;
	}
}