package org.richa.operations;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.richa.config.Application;

/**
 * Operations that can be performed on a field
 * @author ram
 */
public class Field extends BaseOperation
{	
	private String name ;
	
	/**
	 * Show a field
	 */
	public ResponseItem show() 
	{
		return ResponseItem.createResponseItem(name,SHOW) ;
	}
	
	/**
	 * Hide a field
	 */
	public ResponseItem hide()
	{
		return ResponseItem.createResponseItem(name,HIDE) ;
	}
	
	/**
	 * Disable a field
	 */
	public ResponseItem disable() 
	{
		return ResponseItem.createResponseItem(name,DISABLE) ;
	}
	
	/**
	 * Enable a field  
	 */
	public ResponseItem enable() 
	{
		return ResponseItem.createResponseItem(name,ENABLE) ;
	}
	
	/**
	 * Focus the field  
	 */
	public ResponseItem focus() 
	{
		return ResponseItem.createResponseItem(name,FOCUS) ;
	}
	
	/**
	 * Set he field value
	 */
	public ResponseItem set(String value)
	{
		String[] params = new String[1] ;
		params[0] = value ;
		
		return ResponseItem.createResponseItem(name,SET,params) ;
	}
	
	/**
	 * Set the field value based on an integer
	 */
	public ResponseItem set(int value) 
	{
		String temp = Integer.toString(value) ;
		
		return set(temp) ;
	}
	
	/**
	 * Set the field value based on a long
	 */
	public ResponseItem set(String name, long value) 
	{
		String temp = Long.toString(value) ;
		
		return set(temp) ;
	}
	
	/**
	 * Set the field value based on a float 
	 */
	public ResponseItem set(String name, float value) 
	{
		String temp = Float.toString(value) ;
		
		return set(temp) ;
	}
	
	/**
	 * Set the field value based on a double
	 */
	public ResponseItem set(String name, double value) 
	{
		String temp = Double.toString(value) ;
		
		return set(temp) ;		
	}
	
	/**
	 * Set the field value for a date field
	 */
	public ResponseItem set(String name, Date value) 
	{
		SimpleDateFormat fmt = (SimpleDateFormat) Application.getInstance().get("dateformatobj") ;
		
		//Convert the value
		String temp = fmt.format(value) ;
		
		//Set the value
		return set(temp) ;
	}
	
	/**
	 * Set the field value for a timestamp value
	 */
	public ResponseItem set(String name, Timestamp value)
	{
		SimpleDateFormat fmt = (SimpleDateFormat) Application.getInstance().get("timeformatobj") ;
		
		//Convert the value
		String temp = fmt.format(value) ;
		
		//Set the value
		return set(temp) ;
	}
}
