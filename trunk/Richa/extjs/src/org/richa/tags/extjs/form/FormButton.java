package org.richa.tags.extjs.form;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.xml.sax.SAXException;

/**
 * This class represents a FormButton tag
 * @author ram
 *
 */
public class FormButton extends BaseControlTag
{
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		String text = getAttribute("text") ;
		if (isEmpty(text))
			throw new JellyTagException("text is a required parameter for this tag") ;
		
		String handler = getAttribute("handler") ;
		if (isEmpty(handler))
			scriptBuffer.appendln("    " + getCurrentFormName() + ".addButton(\"" + text + "\");") ;
		else
			scriptBuffer.appendln("    " + getCurrentFormName() + ".addButton(\"" + text + "\"," + handler + ");") ;
	}
}