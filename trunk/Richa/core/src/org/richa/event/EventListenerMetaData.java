package org.richa.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventListenerMetaData
{
	private Class eventListener;
	
	private Map<String,Method> eventHandlerMap;
	private Map<String,Method> pageBindHandlerMap;
	private Map<String,Method> dataStoreHandlerMap;
	
	/**
	 * Constructor
	 */
	public EventListenerMetaData()
	{
		super();
	}
	
	/**
	 * Constructor
	 */
	public EventListenerMetaData(Class eventListener)
	{
		this(eventListener, new HashMap<String,Method>(), new HashMap<String,Method>(), new HashMap<String,Method>());
	}
	
	/**
	 * Constructor
	 */
	public EventListenerMetaData(Class eventListener, Map<String,Method> eventHandlerMap, Map<String,Method> pageBindHandlerMap,Map<String,Method> dataStoreHandlerMap)
	{
		this.eventListener = eventListener;
		this.eventHandlerMap = eventHandlerMap;
		this.pageBindHandlerMap = pageBindHandlerMap ;
		this.dataStoreHandlerMap = dataStoreHandlerMap ;
	}

	
	/**
	 * Get the Event Listener
	 */
	public Class getEventListener()
	{
		return eventListener;
	}

	/**
	 * Set Event Listener
	 */
	public void setEventListener(Class eventListener)
	{
		this.eventListener = eventListener;
	}

	/**
	 * Add Event Listener
	 */
	public void addEventHandler(String handlerName, Method handlerMethod )
	{
		eventHandlerMap.put(handlerName, handlerMethod);
	}
	
	/**
	 * Get Event Handler
	 */
	public Method getEventHandler(String handlerName)
	{
		return eventHandlerMap.get(handlerName);
	}
	
	/**
	 * Add a page bind handler
	 */
	public void addPageBindHandler(String handlerName, Method handlerMethod )
	{
		pageBindHandlerMap.put(handlerName, handlerMethod);
	}
	
	/**
	 * Get a page bind handler
	 */
	public Method getPageBindHandler(String handlerName)
	{
		return pageBindHandlerMap.get(handlerName);
	}
	
	/**
	 * Add a data store handler
	 */
	public void addDataStoreHandler(String handlerName, Method handlerMethod )
	{
		dataStoreHandlerMap.put(handlerName, handlerMethod);
	}
	
	/**
	 * Get a data store handler
	 */
	public Method getDataStoreHandler(String handlerName)
	{
		return dataStoreHandlerMap.get(handlerName);
	}
}