package org.richa.runner;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Response object that is used by all the servlets.
 * EventResponse extends this response object
 * @author ram
 *
 */
public class Response
{
	protected static String CODE = "CODE" ;
	protected static String DATA = "DATA" ;
	
	public static String SUCCESS = "SUCCESS" ;
	public static String FAIL = "FAIL" ;

	/**
	 * Response Code
	 */
	protected String code ;
	
	/**
	 * Response data
	 */
	protected String data ;
	
	/**
	 * Default Constructor
	 */
	public Response()
	{
		code = SUCCESS ;
	}
	
	/**
	 * Set the response code as sucess
	 */
	public void success()
	{
		code = SUCCESS ;
	}
	
	/**
	 * Set the response code as fail
	 */
	public void fail(String error)
	{
		code = FAIL ;
		this.data = error ;
	}
	
	/**
	 * Set the response result
	 */
	public void setCode(String code)
	{
		this.code = code ;
	}
	
	/**
	 * Get the response code
	 */
	public String getCode()
	{
		return code ;
	}
	
	
	/**
	 * Set the response date
	 */
	public void setData(String data)
	{
		this.data = data ;
	}
	
	/**
	 * Get the response data
	 */
	public String getData()
	{
		return data ;
	}
	
	/**
	 * Serialize the response item
	 */
	public String serialize() throws JSONException
	{
		JSONObject json = new JSONObject() ;
		
		//Add the items to the JSON object
		json.put(CODE, code) ;
		json.put(DATA, data) ;
		
		//Return the string representation
		return(json.toString()) ;
	}
}