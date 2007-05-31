package org.richa.datastore;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ListDataStore implements DataStore
{
	//Logger
	protected static Log log = LogFactory.getLog(ListDataStore.class);
	
	/**
	 * Holder of data
	 */
	private List data ;
		
	/**
	 * Constructor
	 */
	public ListDataStore()
	{
		data = new LinkedList() ;
	}
	
	/**
	 * Return the collection
	 */
	public List getData()
	{
		return data ;
	}

	/**
	 * Add a bean to the store. If the bean does not match the type an IllegalArgException will be thrown
	 */
	public void add(Object bean)
	{		
		//Add it to the bean
		data.add(bean) ;
	}
	
	/**
	 * Clear the data store
	 */
	public void clean()
	{
		data.clear() ;
	}
}
