package org.richa.event;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richa.scanner.EventListenerScanner;

public class EventListeners
{
	private static Log log = LogFactory.getLog(EventListeners.class); 
	
	//Static instance
	private static EventListeners listeners = null ;
	
	//Collection of listeners
	private Map<String, EventListenerMetaData> eventListeners;
	
	/**
	 * Private Constructor
	 */
	private EventListeners()
	{		
	}
	
	/**
	 * Create the only instance of EventListeners
	 */
	public static EventListeners getInstance()
	{
		if (listeners == null)
		{
			//Create a new listeners instance
			listeners = new EventListeners() ;
			
			//Find event handlers
			listeners.eventListeners = new EventListenerScanner("richa.xml").getClasses();			
		}
		
		return listeners ;
	}
	
	/**
	 * Get the EventListenerMetaData object
	 */
	public EventListenerMetaData get(String eventListener)
	{
		EventListenerMetaData metaData = null ;
		
		if (eventListeners != null)
			metaData = eventListeners.get(eventListener) ;
		
		return metaData ;
	}
	
	/**
	 * Get the event handler method object
	 */
	public Method getHandlerMethod(String eventListener, String eventHandler)
	{
		Method eventMethod = null ;
		
		EventListenerMetaData eventListenerMetaData = listeners.get(eventListener) ;
		if (eventListenerMetaData != null)
		{
			Class eventListenerClass = eventListenerMetaData.getEventListener();

			if (eventListenerClass != null)
			{
				// Find the method that is associated with the eventName
				eventMethod = eventListenerMetaData.getEventHandler(eventHandler);

				if (eventMethod == null)
				{
					log.error("Couldn't find event handler method for the listener: " + eventListener + " and handler: " + eventHandler);
				}
			}
			else
			{
				log.error("Couldn't find event listener class for the listener: " + eventListener);
			}
		}
		else
		{
			log.error("No registered event listener for the listener: " + eventListener);
		}
		
		return eventMethod ;
	}
}
