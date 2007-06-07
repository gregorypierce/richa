package org.richa.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.richa.commands.BorderLayout;
import org.richa.commands.Field;
import org.richa.commands.FieldSet;
import org.richa.commands.Form;
import org.richa.commands.ResponseItem;
import org.richa.commands.TabPanel;
import org.richa.commands.TabPanelItem;
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
	//Logger
	protected static Log log = LogFactory.getLog(EventResponse.class);
	
	/**
	 * Manages field set operations
	 */
	private FieldSet fieldset ;
	
	/**
	 * Manages field operations
	 */
	private Field field ;
	
	/**
	 * Manages form operations
	 */
	private Form form ;
	
	/**
	 * Manages tab panel operations
	 */
	private TabPanel tabPanel ;

	/**
	 * Manages tab panel item operations
	 */
	private TabPanelItem tabPanelItem ;
	
	/**
	 * Manages borderlayout operations
	 */
	private BorderLayout borderLayout ;
	
	
	//Response Fragments
	private List<ResponseItem> responses = new ArrayList<ResponseItem>() ;
	
	/**
	 * Add a new response item
	 */
	public void add(ResponseItem item)
	{
		responses.add(item) ;
		
		log.debug("Adding a Response Item to the EventRespone object") ;
		
		try
		{
			log.debug(item.serialize()) ;
		}
		catch (JSONException e)
		{
			e.printStackTrace() ;
		}
	}
	
	/**
	 * Clear all the operations
	 */
	public void clearOperations()
	{
		responses.clear() ;
		
		log.debug("Clearing the EventRespone object") ;
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
	
		log.debug("Serializing event response" + res.toString()) ;
		
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
		
		log.debug("Getting a field object for EventRespone") ;
		
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
	
		log.debug("Getting a fieldset object for EventRespone") ;
		
		return fieldset ;
	}
	
	/**
	 * Return a TabPanel object for the given name
	 */
	public TabPanel getTabPanel(String name)
	{
		if (tabPanel == null)
			tabPanel = new TabPanel(name, this) ;
		else
		{
			tabPanel.setName(name) ;
			tabPanel.setEventResponse(this) ;
		}
		
		log.debug("Getting a tab panel object for the EventRespone") ;
		
		return tabPanel ;
	}
	
	
	/**
	 * Return a TabPanelItem object for the given name
	 */
	public TabPanelItem getTabPanel(String tabPanel,String name)
	{
		if (tabPanelItem == null)
			tabPanelItem = new TabPanelItem(tabPanel, name, this) ;
		else
		{
			tabPanelItem.setTabPanel(tabPanel) ;
			tabPanelItem.setName(name) ;
			tabPanelItem.setEventResponse(this) ;
		}
		
		log.debug("Getting a tab panel item object for the EventRespone") ;
		
		return tabPanelItem ;
	}
	
	
	/**
	 * Return a BorderLayout object for the given name
	 */
	public BorderLayout getBorderLayout(String name)
	{
		if (borderLayout == null)
			borderLayout = new BorderLayout(name, this) ;
		else
		{
			borderLayout.setName(name) ;
			borderLayout.setEventResponse(this) ;
		}
		
		log.debug("Getting a border layout object for the EventRespone") ;
		
		return borderLayout ;
	}
	
	/**
	 * Returns a Form object for the given name
	 */
	public Form getForm(String name)
	{
		if (form == null)
			form = new Form(name, this) ;
		else
		{
			form.setName(name) ;
			form.setEventResponse(this) ;
		}
		
		log.debug("Getting a form object for the EventRespone") ;
		
		return form ;
	}
}
