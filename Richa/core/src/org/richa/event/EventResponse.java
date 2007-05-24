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
	private FieldSet fieldset ;
	
	/**
	 * Manages field operations
	 */
	private Field field ;
	
	/**
	 * Manages tab operations
	 */
	private Tab tab ;

	/**
	 * Manages border layout operations
	 */
	private BorderLayout borderlayout ;
	
	//Response Fragments
	private List<ResponseItem> responses = new ArrayList<ResponseItem>() ;
	
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
		boolean first = true;
		
		if (code == Response.FAIL)
			return super.serialize() ;
		
		//Create a response object
		JSONObject res = new JSONObject() ;
		res.put(Response.CODE, getCode()) ;
		
		//Script buffer
		AppendingStringBuffer script = new AppendingStringBuffer() ;
		
		//Get the iterator keys
		Iterator iter = responses.iterator() ;
	
		script.append("[") ;
		
		//Loop through all the keys
		while (iter.hasNext())
		{
			//Get the item
			ResponseItem item = (ResponseItem) iter.next() ;
			
			if (!first)
				script.append(",") ;
			else
				first = false ;
			
			//Append it to the buffer
			script.append(item.serialize()) ;
		}
	
		script.append("]") ;
		
		//Set the data
		res.put(Response.DATA, script.toString()) ;
		
		//Generate the response
		return res.toString() ;
	}
	
	/**
	 * Return a field object for the given name
	 */
	public Field getField(String name)
	{
		if (field == null)
			field = new Field(name, this) ;
		else
		{
			field.setName(name) ;
			field.setEventResponse(this) ;
		}
		
		return field ;
	}
	
	/**
	 * Return a fieldset object for the given name
	 */
	public FieldSet getFieldSet(String name)
	{
		if (fieldset == null)
			fieldset = new FieldSet(name, this) ;
		else
		{
			fieldset.setName(name) ;
			fieldset.setEventResponse(this) ;
		}
		
		return fieldset ;
	}
	/**
	 * Return a Tab object for the given name
	 */
	public Tab getTab(String name)
	{
		if (tab == null)
			tab = new Tab(name, this) ;
		else
		{
			tab.setName(name) ;
			tab.setEventResponse(this) ;
		}
		
		return tab ;
	}
}
