package org.richa.config;

import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * Richa will load a set of properties from the config file 
 * during startup
 * 
 * @author ram
 *
 */
public class Application
{
	private static HashMap<String,Object> properties = new HashMap<String,Object>() ;

	static
	{
		//Put the default date format string 
		properties.put("dateformatstr", "MM/dd/YYYY") ;
		
		//Put the SimpleDateFormat object
		properties.put("dateformatobj", new SimpleDateFormat((String)properties.get("defaultdateformat")));
				
		//Put the default time format string 
		properties.put("timeformatstr", "MM/dd/YYYY hh:mm:ss") ;
		
		//Put the SimpleDateFormat object
		properties.put("timeformatobj", new SimpleDateFormat((String)properties.get("defaultdateformat")));

	}
	
	/**
	 * Get the application property
	 */
	public static Object get(String name)
	{
		return (properties.get(name)) ;
	}
	
	/**
	 * Set the application property
	 */
	public static void set(String name, Object value)
	{
		properties.put(name, value) ;
	}
}
