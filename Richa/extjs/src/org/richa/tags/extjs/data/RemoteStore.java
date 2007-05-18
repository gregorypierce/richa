package org.richa.tags.extjs.data ;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.tags.extjs.BaseExtJSTag;
import org.xml.sax.SAXException;

/**
 * This class represents a RemoteStore tag. 
 * This tag is used to define a store that can be bound to controls like ComboBox and Grid 
 * @author ram
 *
 */
public class RemoteStore extends BaseExtJSTag
{
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		//Get the name
		String name = getName() ;
		if (isEmpty(name))
			throw new JellyTagException("name is a required parameter for this tag") ;
		
		//Get the bean name
		String bean = (String)getAttribute(BEAN) ;
		if (isEmpty(bean))
			throw new JellyTagException("bean is a required parameter for this tag") ;
		
	}
	
	/**
	 * Generate html and js after the body is processed
	 */
	protected void afterBody(final XMLOutput output) throws JellyTagException, SAXException
	{

	}
}

