package org.richa.tags.extjs.event;

import java.lang.reflect.Method;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.event.EventListeners;
import org.richa.tags.extjs.BaseExtJSTag;
import org.xml.sax.SAXException;

/**
 * This class represents a EventListener tag
 * @author ram
 *
 */
public class EventListener extends BaseExtJSTag
{	
	/**
	 * Process the start tag
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		//Get the name
		String name = getName() ;
		if (isEmpty(name))
			throw new JellyTagException("name is a required parameter for this tag") ;
		
		//Get the bind handler
		String bind = getAttribute(BIND) ;
		if (!isEmpty(bind))
		{
			//Get the method
			EventListeners listeners = EventListeners.getInstance() ;
			if (listeners != null)
			{
				Method method = listeners.getHandlerMethod(name, bind) ;
				
				try
				{
					//Invoke the method
					method.invoke(name) ;
				}
				catch (Exception e)
				{
					log.error("Unable to invoke method in listener: " + name + " and handler: " + bind) ;
				}
			}
		}
		
		//Add the web app name and the path name
		name = webContext + "/event/" + name ;
		
		//Add the listener to the stack
		addListener(name) ;
	}
	
	/**
	 * Process the end tag
	 */
	protected void afterBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		//Remove the listener
		removeCurrentListener() ;
	}
	
	
}

