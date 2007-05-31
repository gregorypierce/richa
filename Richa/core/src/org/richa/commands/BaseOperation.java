package org.richa.commands;

import org.richa.event.EventResponse;

public class BaseCommand
{
	/**
	 * Show the object
	 */
	protected static final String SHOW = "show" ;
	
	/**
	 * Hide the object
	 */
	protected static final String HIDE = "hide" ;
	
	/**
	 * Disable the object
	 */
	protected static final String DISABLE = "disable" ;
	
	/**
	 * Enable the object
	 */
	protected static final String ENABLE = "enable" ;
	
	/**
	 * Set the object's value
	 */
	protected static final String SET = "set" ;
	
	/**
	 * Set the focus the object
	 */
	protected static final String FOCUS = "focus" ;
	
	/**
	 * Add an item
	 */
	protected static final String ADDITEM = "additem" ;
	
	/**
	 * Expand a region
	 */
	protected static final String EXPAND = "expand" ;

	/**
	 * Collapse a region
	 */
	protected static final String COLLAPSE = "collapse" ;

	//Name of the object
	protected String name ;
	
	//Response object that tracks all the operations
	protected EventResponse res ;
	
	/**
	 * Constructor
	 */
	public BaseOperation(String name, EventResponse res)
	{
		this.name = name ;
		this.res = res ;
	}
	
	/**
	 * Set the name of the field
	 */
	public void setName(String name)
	{
		this.name = name ;
	}
	
	/**
	 * Get the name of the field
	 */
	public String getName()
	{
		return name ;
	}
	
	
	/**
	 * Set the EventResponse object that tracks all the operations 
	 * on this field object
	 */
	public void setEventResponse(EventResponse res)
	{
		this.res = res ;
	}
}
