package org.richa.commands;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.richa.config.Application;
import org.richa.event.EventResponse;

/**
 * Operations that can be performed on a form object
 * @author ram
 */
public class Form extends BaseCommand
{	
	/**
	 * Constructor
	 */
	public Form (String name, EventResponse res)
	{
		super(name,res) ;
	}
	
	/**
	 * Set the field value
	 */
	public void set(Object value)
	{
		JSONArray params = new JSONArray() ;
		JSONObject obj = new JSONObject() ;
		
		try
		{
			Map props = PropertyUtils.describe(value) ;
			
			//Get a list of all properties
			Iterator iter = props.keySet().iterator() ;
			
			while(iter.hasNext())
			{
				String prop = (String) iter.next() ;
				
				Object propValue = props.get(prop) ;
				if (propValue instanceof java.util.Date || propValue instanceof java.sql.Date)
				{
					SimpleDateFormat fmt = (SimpleDateFormat) Application.getInstance().get("dateformatobj") ;
					
					//Convert the value
					propValue = fmt.format(propValue) ;
				}
				else if (propValue instanceof Timestamp)
				{
					SimpleDateFormat fmt = (SimpleDateFormat) Application.getInstance().get("timeformatobj") ;
					
					//Convert the value
					propValue = fmt.format(propValue) ;
				}
				
				obj.put(prop,propValue) ;
			}
		}
		catch (JSONException e)
		{
			log.error(e) ;
		}
		catch (NoSuchMethodException e)
		{
			log.error(e) ;
		}
		catch (InvocationTargetException e)
		{
			log.error(e) ;
		}
		catch (IllegalAccessException e)
		{
			log.error(e) ;
		}
		
		params.put(obj) ;
		
		res.add(ResponseItem.createResponseItem(name,SET,params)) ;
	}	
}
