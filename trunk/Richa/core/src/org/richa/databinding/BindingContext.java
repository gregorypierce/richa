package org.richa.databinding;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Set of variables that are used to Bind data to the Richa Page
 * The EL expressions in the page use these variables to get data 
 * for the controls on the page
 * 
 * @author ram
 *
 */
public class BindingContext
{
	/**
	 * Holder to store all the variables that need to be bound
	 */
	private Map<String,Object> variables ;
	
	
	/**
	 * Default Constructor
	 */
	public BindingContext()
	{
		variables = new HashMap<String,Object>() ;
	}
	
	/**
	 * Add a key/value pair to the BindingContext
	 */
	public Object get(String key)
	{
		return (variables.get(key)) ;
	}
	
	/**
	 * Add a key/value pair to the BindingContext
	 */
	public void add(String key, Object value)
	{
		variables.put(key, value) ;
	}
	
	/**
	 * Remove a key from the Binding Context
	 */
	public void remove(String key)
	{
		variables.remove(key) ;
	}
	
	/**
	 * Clear all the variables in the context
	 */
	public void clear()
	{
		variables.clear() ;
	}
	
	/**
	 * Get the iterator on the binding variables
	 */
	public Iterator getIterator()
	{
		return (variables.keySet().iterator()) ;
	}
}
