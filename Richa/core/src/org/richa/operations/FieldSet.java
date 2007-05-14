package org.richa.operations;

/**
 * Operations that can be performed on a form
 * @author ram
 *
 */
public class FieldSet extends BaseOperation
{
	private String name ;
	
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
	 * Enable the form
	 */
	public ResponseItem enable()
	{
		return ResponseItem.createResponseItem(name,ENABLE) ;
	}
}
