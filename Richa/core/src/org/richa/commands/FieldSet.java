package org.richa.commands;

import org.richa.event.EventResponse;

/**
 * Operations that can be performed on a form
 * @author ram
 *
 */
public class FieldSet extends BaseCommand
{
	private static final String FIELDSET = "fieldset" ;
	
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
		res.add(ResponseItem.createResponseItem(name,FIELDSET,SHOW)) ;
	}
	
	/**
	 * Hide the fieldset
	 */
	public void hide() 
	{
		res.add(ResponseItem.createResponseItem(name,FIELDSET,HIDE)) ;
	}
	
	/**
	 * Focus the field  
	 */
	public void focus() 
	{
		res.add(ResponseItem.createResponseItem(name,FIELDSET,FOCUS)) ;
	}
	/**
	 * Disable the fielset
	 */
	public void disable()
	{
		res.add(ResponseItem.createResponseItem(name,FIELDSET,DISABLE)) ;
	}
	
	/**
	 * Enable the fieldset
	 */
	public void enable()
	{
		res.add(ResponseItem.createResponseItem(name,FIELDSET,ENABLE)) ;
	}
}
