package org.richa.commands;

import org.richa.event.EventResponse;

/**
 * Operations that can be performed on a tab
 * @author ram
 *
 */
public class Tab extends BaseCommand 
{
	/**
	 * Constuctor
	 */
	public Tab (String name, EventResponse res)
	{
		super (name, res) ;
	}
	
	/**
	 * Show the tab
	 */
	public void show() 
	{
		res.add(ResponseItem.createResponseItem(name,HIDE)) ;
	}
	
	/**
	 * Hide the tab
	 */
	public void hide(String name)
	{
		res.add(ResponseItem.createResponseItem(name,HIDE)) ;
	}
	
	/**
	 * Disable the tab
	 */
	public void disable(String name) 
	{
		res.add(ResponseItem.createResponseItem(name,DISABLE)) ;
	}
	
	/**
	 * Enable the tab
	 */
	public void enable(String name) 
	{
		res.add(ResponseItem.createResponseItem(name,ENABLE)) ;
	}
	
	/**
	 * Add a tab item
	 */
	public void addItem(String name) 
	{
		res.add(ResponseItem.createResponseItem(name,ADDITEM)) ;
	}
}
