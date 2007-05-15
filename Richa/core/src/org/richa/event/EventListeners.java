package org.richa.event;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richa.scanner.EventListenerScanner;

/**
 * Singleton that holds the collection of the Eventlisteners in the application. 
 * It also has helper methods to get class level metadata from the listeners
 * @author ram
 *
 */
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
	public Method getEventHandlerMethod(String eventListener, String eventHandler)
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
	
	/**
	 * Get the event handler method object
	 */
	public Method getBindHandlerMethod(String eventListener, String bindHandler)
	{
		Method bindMethod = null ;
		
		EventListenerMetaData eventListenerMetaData = listeners.get(eventListener) ;
		if (eventListenerMetaData != null)
		{
			Class eventListenerClass = eventListenerMetaData.getEventListener();

			if (eventListenerClass != null)
			{
				// Find the method that is associated with the bind event name
				bindMethod = eventListenerMetaData.getBindHandler(bindHandler);

				if (bindMethod == null)
				{
					log.error("Couldn't find bind handler method for the listener: " + eventListener + " and handler: " + bindHandler);
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
		
		return bindMethod ;
	}
	
	/**
	 * Get the event listener object
	 */
	public Object getEventListener(String eventListener) throws InstantiationException, IllegalAccessException
	{		
		Object listener = null ;
		
		EventListenerMetaData eventListenerMetaData = listeners.get(eventListener) ;
		if (eventListenerMetaData != null)
		{
			Class eventListenerClass = eventListenerMetaData.getEventListener();
			if (eventListenerClass != null)
			{
				listener = eventListenerClass.newInstance() ;
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
		
		return listener ;
	}
}
