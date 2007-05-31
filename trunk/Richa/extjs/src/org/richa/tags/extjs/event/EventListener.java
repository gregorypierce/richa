package org.richa.tags.extjs.event;

import java.lang.reflect.Method;
import java.util.Iterator;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.databinding.BindingContext;
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
		String bind = (String) getAttribute(BIND) ;
		if (!isEmpty(bind))
		{
			//Get the method
			EventListeners listeners = EventListeners.getInstance() ;
			if (listeners != null)
			{
				Method method = listeners.getPageBindHandlerMethod(name, bind) ;
				
				try
				{
					//Create a listener object
					Object listener = listeners.getEventListener(name) ;
					
					//Create new binding context
					BindingContext context = new BindingContext() ;
					
					//Set up the parameters for the bean
					Object[] params = new Object[1] ;
					params[0] = context ;
					
					//Invoke the method
					method.invoke(listener, params) ;
					
					//Loop through all the beans and add them to the context
					Iterator iterbeans = context.getIterator() ;
					while (iterbeans.hasNext())
					{
						String key = (String)iterbeans.next() ;
						Object bean = context.get(key) ;
						
						//Add 
						addBindingVariable(key,bean) ;
					}
				}
				catch (Exception e)
				{
					log.error("Unable to invoke method in listener: " + name + " and handler: " + bind + " Exception:" + e) ;
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

