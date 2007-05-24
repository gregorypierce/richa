package org.richa.operations;

import org.richa.event.EventResponse;

/**
 * Operations that can be performed on a form
 * @author ram
 *
 */
public class FieldSet extends BaseOperation
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
	public ResponseItem show() 
	{
		return ResponseItem.createResponseItem(name,SHOW) ;
	}
	
	/**
	 * Hide the fieldset
	 */
	public ResponseItem hide() 
	{
		return ResponseItem.createResponseItem(name,HIDE) ;
	}
	
	/**
	 * Focus the field  
	 */
	public ResponseItem focus() 
	{
		return ResponseItem.createResponseItem(name,FOCUS) ;
	}
	/**
	 * Disable the fielset
	 */
	public ResponseItem disable()
	{
		return ResponseItem.createResponseItem(name,DISABLE) ;
	}
	
	/**
	 * Enable the fieldset
	 */
	public ResponseItem enable()
	{
		return ResponseItem.createResponseItem(name,ENABLE) ;
	}
}
