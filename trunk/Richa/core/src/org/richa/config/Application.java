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
	private static Application application ;
	
	private HashMap<String,Object> properties = new HashMap<String,Object>() ;
	
	//private constructor
	private Application()
	{
		
	}
	
	//Get the singleton instance
	public static Application getInstance()
	{
		if (application == null)
		{
			application = new Application() ;
	
			//Caching enabled
			application.set("cachingenabled", "true") ;
			
			//Put the default date format string 
			application.set("dateformatstr", "MM/dd/yy") ;
			
			//Put the SimpleDateFormat object
			application.set("dateformatobj", new SimpleDateFormat((String)application.get("dateformatstr")));
					
			//Put the default time format string 
			application.set("timeformatstr", "MM/dd/yy hh:mm:ss") ;
			
			//Put the SimpleDateFormat object
			application.set("timeformatobj", new SimpleDateFormat((String)application.get("timeformatstr")));
		}
		
		return application ;
	}
	
	/**
	 * Get the application property
	 */
	public Object get(String name)
	{
		return (properties.get(name)) ;
	}
	
	/**
	 * Set the application property
	 */
	public  void set(String name, Object value)
	{
		properties.put(name, value) ;
	}
}
