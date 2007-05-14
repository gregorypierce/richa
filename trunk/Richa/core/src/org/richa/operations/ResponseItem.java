package org.richa.operations;

import java.util.LinkedList;
import java.util.List;

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
	private static final  String OPERATION = "operation" ;
	private static final  String PARAMS = "params" ;
	
	
	//Name of the object to perform the operation
	private String name ;
	
	//Operation to be performed
	private String operation ;
	
	//Parameters to be passed to the operation
	private String[] params ;
	
	//Constructor
	private ResponseItem()
	{
		params = null ;
		name = EMPTY ;
		operation = EMPTY ;
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
	 * Get the parameters to be passed to the operation
	 */
	public String[] getParams()
	{
		return params;
	}

	/**
	 * Set the parameters to be passed to the operation
	 */
	public void setParams(String[] params)
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
		json.put(OPERATION, operation) ;
		json.put(PARAMS, params) ;
		
		//Return the string representation
		return(json.toString()) ;
	}
	
	/**
	 * Helper method to create a response item based on the name and operation
	 */
	public static ResponseItem createResponseItem(String name, String operation)
	{
		ResponseItem item = new ResponseItem() ;
		
		//Set the attributes
		item.setName(name) ;
		item.setOperation(operation) ;
		
		return item ;
	}
	
	/**
	 * Helper method to create a response item based on the name, operation and parameters
	 */
	public static ResponseItem createResponseItem(String name, String operation, String[] params)
	{
		//Create the response item
		ResponseItem item = createResponseItem(name,operation) ;
		
		//Set the parameters
		item.setParams(params) ;
		
		return item ;
	}
}
