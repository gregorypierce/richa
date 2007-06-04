package org.richa.commands;

import org.richa.event.EventResponse;

/**
 * Operations that can be performed on a form
 * @author ram
 *
 */
public class FieldSet extends BaseCommand
{
	/**
	 * Constructor
	 */
	public FieldSet(String name, EventResponse res)
	{
		super(name,res) ;
	}
	
	/**
	 * Show the form
	 */
	public void show() 
	{
		res.add(ResponseItem.createResponseItem(name,SHOW)) ;
	}
	
	/**
	 * Hide the fieldset
	 */
	public void hide() 
	{
		res.add(ResponseItem.createResponseItem(name,HIDE)) ;
	}
	
	/**
	 * Focus the field  
	 */
	public void focus() 
	{
		res.add(ResponseItem.createResponseItem(name,FOCUS)) ;
	}
	/**
	 * Disable the fielset
	 */
	public void disable()
	{
		res.add(ResponseItem.createResponseItem(name,DISABLE)) ;
	}
	
	/**
	 * Enable the fieldset
	 */
	public void enable()
	{
		res.add(ResponseItem.createResponseItem(name,ENABLE)) ;
	}
}
