package org.richa.tags.extjs.form;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.xml.sax.SAXException;

/**
 * This class represents a FormButton tag
 * 
 * @author ram
 * 
 */
public class FormButton extends BaseControlTag
{
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException,
			SAXException
	{
		String text = getAttribute("text");
		if (isEmpty(text))
			throw new JellyTagException(
					"text is a required parameter for this tag");

		String handler = getAttribute("handler");
		if (isEmpty(handler))
			scriptBuffer.appendln("    " + getCurrentForm().getName()
					+ ".addButton(\"" + text + "\");");
		else
		{
			// Find the appropriate listener
			String listener = getCurrentForm().getListener();
			if (listener == null || "".equals(listener.trim()))
			{
				listener = getCurrentPage().getListener();
			}
			if (listener == null || "".equals(listener.trim()))
			{
				throw new JellyTagException(
						"If you define a handler for an element, you must define a listener in the parent form or page element");
			}
			String scope = getAttribute("scope");
			if ("form".equals(scope))
			{
				scriptBuffer.appendln("    currentButton = "
						+ getCurrentForm().getName() + ".addButton(\"" + text
						+ "\",function() {handleEvent(\"" + listener + "\", \""
						+ handler + "\", " + getCurrentForm().getName()
						+ ".getValues())});");
			}
			// TODO: Need more scopes that can be handled
			// This is a simple handler
			else
			{
				scriptBuffer.appendln("    currentButton = "
						+ getCurrentForm().getName() + ".addButton(\"" + text
						+ "\",function() {handleEvent(\"" + listener + "\", \""
						+ handler + "\")});");
			}

		}
	}
}