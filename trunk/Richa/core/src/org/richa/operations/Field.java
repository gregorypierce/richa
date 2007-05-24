package org.richa.operations;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.richa.config.Application;
import org.richa.event.EventResponse;

/**
 * Operations that can be performed on a field
 * @author ram
 */
public class Field extends BaseOperation
{	
	/**
	 * Constructor
	 */
	public Field(String name, EventResponse res)
	{
		super(name,res) ;
	}
	
	/**
	 * Show a field
	 */
	public void show() 
	{
		res.add(ResponseItem.createResponseItem(name,SHOW)) ;
	}
	
	/**
	 * Hide a field
	 */
	public void hide()
	{
		res.add(ResponseItem.createResponseItem(name,HIDE)) ;
	}
	
	/**
	 * Disable a field
	 */
	public void disable() 
	{
		res.add(ResponseItem.createResponseItem(name,DISABLE)) ;
	}
	
	/**
	 * Enable a field  
	 */
	public void enable() 
	{
		res.add(ResponseItem.createResponseItem(name,ENABLE)) ;
	}
	
	/**
	 * Focus the field  
	 */
	public void focus() 
	{
		res.add(ResponseItem.createResponseItem(name,FOCUS)) ;
	}
	
	/**
	 * Set he field value
	 */
	public void set(String value)
	{
		String[] params = new String[1] ;
		params[0] = value ;
		
		res.add(ResponseItem.createResponseItem(name,SET,params)) ;
	}
	
	/**
	 * Set the field value based on an integer
	 */
	public void set(int value) 
	{
		String temp = Integer.toString(value) ;
		
		set(temp) ;
	}
	
	/**
	 * Set the field value based on a long
	 */
	public void set(String name, long value) 
	{
		String temp = Long.toString(value) ;
		
		set(temp) ;
	}
	
	/**
	 * Set the field value based on a float 
	 */
	public void set(String name, float value) 
	{
		String temp = Float.toString(value) ;
		
		set(temp) ;
	}
	
	/**
	 * Set the field value based on a double
	 */
	public void set(String name, double value) 
	{
		String temp = Double.toString(value) ;
		
		set(temp) ;		
	}
	
	/**
	 * Set the field value for a date field
	 */
	public void set(String name, Date value) 
	{
		SimpleDateFormat fmt = (SimpleDateFormat) Application.getInstance().get("dateformatobj") ;
		
		//Convert the value
		String temp = fmt.format(value) ;
		
		//Set the value
		set(temp) ;
	}
	
	/**
	 * Set the field value for a timestamp value
	 */
	public void set(String name, Timestamp value)
	{
		SimpleDateFormat fmt = (SimpleDateFormat) Application.getInstance().get("timeformatobj") ;
		
		//Convert the value
		String temp = fmt.format(value) ;
		
		//Set the value
		set(temp) ;
	}
}
