package org.richa.commands;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class represents one of the items in the response.
 * An EventResponse contains a collection of ResponseItem objects
 * 
 * @author ram
 *
 */
public class ResponseItem
{
	private static final  String EMPTY = "" ;
	
	private static final  String NAME = "name" ;
	private static final  String OBJECTTYPE = "objecttype" ;
	private static final  String OPERATION = "operation" ;
	private static final  String PARAMS = "params" ;
	
	
	//Name of the object to perform the operation
	private String name ;
	
	//Type of the object
	private String objecttype ;
	
	//Operation to be performed
	private String operation ;
	
	//Parameters to be passed to the operation
	private JSONArray params ;
	
	//Constructor
	private ResponseItem()
	{
		params = null ;
		name = EMPTY ;
		operation = EMPTY ;
		objecttype = EMPTY ;
	}
	
	/**
	 * Set the name for the object to perform the operation on 
	 */
	public String getName()
	{
		return name;
	}


	/**
	 * Get the name for the object to perform the operation on
	 */
	public void setName(String name)
	{
		this.name = name;
	}


	/**
	 * Get the operation to perform 
	 */
	public String getOperation()
	{
		return operation;
	}

	/**
	 * Set the operation to perform
	 */
	public void setOperation(String operation)
	{
		this.operation = operation;
	}
	
	/**
	 * Get the object type 
	 */
	public String getObjectType()
	{
		return objecttype;
	}

	/**
	 * Set the object type
	 */
	public void setObjectType(String objecttype)
	{
		this.objecttype = objecttype;
	}

	/**
	 * Get the parameters to be passed to the operation
	 */
	public JSONArray getParams()
	{
		return params;
	}

	/**
	 * Set the parameters to be passed to the operation
	 */
	public void setParams(JSONArray params)
	{
		this.params = params;
	}

	/**
	 * Serialize a response item to JSON
	 */
	public String serialize() throws JSONException
	{
		JSONObject json = new JSONObject() ;
		
		//Add the items to the JSON object
		json.put(NAME, name) ;
		json.put(OBJECTTYPE, objecttype) ;
		json.put(OPERATION, operation) ;
		json.put(PARAMS, params) ;
		
		//Return the string representation
		return(json.toString()) ;
	}
	
	/**
	 * Helper method to create a response item based on the name and operation
	 */
	public static ResponseItem createResponseItem(String name, String objecttype, String operation)
	{
		ResponseItem item = new ResponseItem() ;
		
		//Set the attributes
		item.setName(name) ;
		item.setObjectType(objecttype) ;
		item.setOperation(operation) ;
		
		return item ;
	}
	
	/**
	 * Helper method to create a response item based on the name, operation and parameters
	 */
	public static ResponseItem createResponseItem(String name, String objecttype, String operation, JSONArray params)
	{
		//Create the response item
		ResponseItem item = createResponseItem(name,objecttype,operation) ;
		
		//Set the parameters
		item.setParams(params) ;
		
		return item ;
	}
}
