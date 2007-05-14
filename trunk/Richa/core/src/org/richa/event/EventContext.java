package org.richa.event;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

/**
 * EventContext object is passed to all the event handlers 
 * @author ram
 *
 */
public class EventContext
{
	private Map data ;
	
	/**
	 * Default constructor
	 *
	 */
	public EventContext()
	{
		data = new HashMap() ;
	}
	
	/**
	 * Add a name/value pair to the context
	 * @param name
	 * @param value
	 */
	public void add(String name, String value)
	{
		data.put(name, value) ;
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
	public void log(Logger log) 
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
}
