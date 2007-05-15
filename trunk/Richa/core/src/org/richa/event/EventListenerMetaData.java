package org.richa.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventListenerMetaData
{
	private Class eventListener;
	
	private Map<String,Method> eventHandlerMap;
	private Map<String,Method> bindHandlerMap;
	
	public EventListenerMetaData()
	{
		super();
	}
	
	public EventListenerMetaData(Class eventListener)
	{
		this(eventListener, new HashMap<String,Method>(), new HashMap<String,Method>());
	}
	
	public EventListenerMetaData(Class eventListener, Map<String,Method> eventHandlerMap, Map<String,Method> bindHandlerMap)
	{
		this.eventListener = eventListener;
		this.eventHandlerMap = eventHandlerMap;
		this.bindHandlerMap = bindHandlerMap ;
	}

	public Class getEventListener()
	{
		return eventListener;
	}

	public void setEventListener(Class eventListener)
	{
		this.eventListener = eventListener;
	}

	public void addEventHandler(String handlerName, Method handlerMethod )
	{
		eventHandlerMap.put(handlerName, handlerMethod);
	}
	
	public Method getEventHandler(String handlerName)
	{
		return eventHandlerMap.get(handlerName);
	}
	
	public void addBindHandler(String handlerName, Method handlerMethod )
	{
		bindHandlerMap.put(handlerName, handlerMethod);
	}
	
	public Method getBindHandler(String handlerName)
	{
		return bindHandlerMap.get(handlerName);
	}
}