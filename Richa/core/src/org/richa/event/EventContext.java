package org.richa.event;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * EventContext object is passed to all the event handlers 
 * @author ram
 *
 */
public class EventContext
{
	//Logger
	protected static Log log = LogFactory.getLog(EventContext.class);
	
	//Data
	private Map<String,String> data ;
	
	/**
	 * Default constructor
	 *
	 */
	public EventContext()
	{
		data = new HashMap<String,String>() ;
	}
	
	/**
	 * Add a name/value pair to the context
	 */
	public void add(String name, String value)
	{
		data.put(name, value) ;
	}
	
	/**
	 * Get the value for a value in the context
	 */
	public String get(String name)
	{
		return ((String) data.get(name)) ;
	}
	
	/**
	 * Remove a name/value pair from the context
	 */
	public void remove(String name)
	{
		data.remove(name) ;
	}
	
	/**
	 * Clear all the data in the context
	 */
	public void clear()
	{
		data.clear() ;
	}
	
	
	/**
	 * Log the context data
	 */
	public void log() 
	{
		log.info("Context Data......................") ;
		
		//Loop through all the fields in the 
		Iterator iter = data.keySet().iterator() ;
		while (iter.hasNext())
		{
			String name = (String) iter.next() ;
			String value = (String) data.get(name) ;
			log.info("Name:" + name + " Value:" + value) ;
		}
	}
	
	/**
	 * Print the context to the console
	 */
	public void print() 
	{
		System.out.println("Context Data......................") ;
		
		//Loop through all the fields in the 
		Iterator iter = data.keySet().iterator() ;
		while (iter.hasNext())
		{
			String name = (String) iter.next() ;
			String value = (String) data.get(name) ;
			System.out.println("Name:" + name + " Value:" + value) ;
		}
	}
	
	/**
	 * Populate a bean with the data in the context
	 */
	public void populateBean(Object bean) 
	{
		try
		{
			BeanUtils.populate(bean, data) ;
		}
		catch (InvocationTargetException e)
		{
			log.error(this,e);
		}
		catch (IllegalAccessException e)
		{
			log.error(this,e);
		}
	}
}
