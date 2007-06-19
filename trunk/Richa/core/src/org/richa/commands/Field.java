package org.richa.commands;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.richa.config.Application;
import org.richa.event.EventResponse;

/**
 * Operations that can be performed on a field
 * @author ram
 */
public class Field extends BaseCommand
{	
	private static final String FIELD = "field" ;
	
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
		res.add(ResponseItem.createResponseItem(name,FIELD,SHOW)) ;
	}
	
	/**
	 * Hide a field
	 */
	public void hide()
	{
		res.add(ResponseItem.createResponseItem(name,FIELD,HIDE)) ;
	}
	
	/**
	 * Disable a field
	 */
	public void disable() 
	{
		res.add(ResponseItem.createResponseItem(name,FIELD,DISABLE)) ;
	}
	
	/**
	 * Enable a field  
	 */
	public void enable() 
	{
		res.add(ResponseItem.createResponseItem(name,FIELD,ENABLE)) ;
	}
	
	/**
	 * Focus the field  
	 */
	public void focus() 
	{
		res.add(ResponseItem.createResponseItem(name,FIELD,FOCUS)) ;
	}
	
	/**
	 * Set he field value
	 */
	public void set(String value)
	{
		JSONArray params = new JSONArray() ;
		params.put(value) ;
		
		res.add(ResponseItem.createResponseItem(name,FIELD,SET,params)) ;
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
	public void set(long value) 
	{
		String temp = Long.toString(value) ;
		
		set(temp) ;
	}
	
	/**
	 * Set the field value based on a float 
	 */
	public void set(float value) 
	{
		String temp = Float.toString(value) ;
		
		set(temp) ;
	}
	
	/**
	 * Set the field value based on a double
	 */
	public void set(double value) 
	{
		String temp = Double.toString(value) ;
		
		set(temp) ;		
	}
	
	/**
	 * Set the field value for a date field
	 */
	public void set(Date value) 
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
	public void set(Timestamp value)
	{
		SimpleDateFormat fmt = (SimpleDateFormat) Application.getInstance().get("timeformatobj") ;
		
		//Convert the value
		String temp = fmt.format(value) ;
		
		//Set the value
		set(temp) ;
	}
}
