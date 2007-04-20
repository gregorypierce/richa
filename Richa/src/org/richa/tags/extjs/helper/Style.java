package org.richa.tags.extjs.helper ;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.tags.extjs.BaseExtJSTag;
import org.xml.sax.SAXException;

/**
 * This class represents a Style tag. This tag is used to include all the
 * scripts required by the framework. Include this in the head of the html
 * @author ram 
 *
 */
public class Style extends BaseExtJSTag
{
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		output.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + webContext + "/resources/css/ext-all.css\"/>\n") ;
	}
}