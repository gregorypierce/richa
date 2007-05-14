package org.richa.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.richa.operations.BorderLayout;
import org.richa.operations.Field;
import org.richa.operations.FieldSet;
import org.richa.operations.Grid;
import org.richa.operations.Menu;
import org.richa.operations.ResponseItem;
import org.richa.operations.Tab;
import org.richa.operations.Toolbar;
import org.richa.operations.Tree;
import org.richa.runner.Response;
import org.richa.util.AppendingStringBuffer;

/**
 * EventResponse object that is returned by the event handler 
 * This object is also used by the servlets to send an error back to the browser
 * @author ram
 *
 */
public class EventResponse  extends Response
{
	/**
	 * Manages field set operations
	 */
	private FieldSet fieldSet ;
	
	/**
	 * Manages field operations
	 */
	private Field field ;
	
	/**
	 * Manages grid operations
	 */
	private Grid grid ;
	
	/**
	 * Manages tab operations
	 */
	private Tab tab ;
	
	/**
	 * Manages toolbar operations
	 */
	private Toolbar toolbar ;
	
	/**
	 * Manages menu operations
	 */  
	private Menu menu ;
	
	/**
	 * Manages tree operations
	 */
	private Tree tree ;
	
	/**
	 * Manages border layout operations
	 */
	private BorderLayout borderlayout ;
	
	//Response Fragments
	private List<ResponseItem> responses = new ArrayList<ResponseItem>() ;
	
	//Result code
	private String resultCode ;
	
	//Error Description
	private String errorDesc ;
	
	/**
	 * Add a new response item
	 */
	public void add(ResponseItem item)
	{
		responses.add(item) ;
	}
	
	/**
	 * Clear all the operations
	 */
	public void clearOperations()
	{
		responses.clear() ;
	}
	
	/**
	 * Serialize the response
	 */
	public String serialize() throws JSONException
	{
		if (code == Response.FAIL)
			return super.serialize() ;
		
		//Create a response object
		JSONObject res = new JSONObject() ;
		res.put(Response.CODE, getCode()) ;
		
		//Script buffer
		AppendingStringBuffer script = new AppendingStringBuffer() ;
		
		//Get the iterator keys
		Iterator iter = responses.iterator() ;
	
		//Loop through all the keys
		while (iter.hasNext())
		{
			//Get the item
			ResponseItem item = (ResponseItem) iter.next() ;
			
			//Append it to the buffer
			script.append(item.serialize()) ;
		}
	
		//Set the data
		res.put(Response.DATA, script.toString()) ;
		
		//Generate the response
		return res.toString() ;
	}
}
