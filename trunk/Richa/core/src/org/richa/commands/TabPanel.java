package org.richa.commands;

import org.json.JSONArray;
import org.richa.event.EventResponse;

/**
 * Operations that can be performed on a tab panel
 * @author ram
 *
 */
public class TabPanel extends BaseCommand 
{
	private static final String TABPANEL = "tabpanel" ;

	/**
	 * Constuctor
	 */
	public TabPanel (String name, EventResponse res)
	{
		super (name, res) ;
	}
	
	/**
	 * Show the tab
	 */
	public void show(String tabPanelItem) 
	{
		JSONArray params = new JSONArray() ;
		params.put(tabPanelItem) ;
		
		res.add(ResponseItem.createResponseItem(name,TABPANEL,SHOW,params)) ;
	}

	
	/**
	 * Hide the tab
	 */
	public void hide(String tabPanelItem)
	{
		JSONArray params = new JSONArray() ;
		params.put(tabPanelItem) ;
		
		res.add(ResponseItem.createResponseItem(name,TABPANEL,HIDE,params)) ;
	}
	
	/**
	 * Enable the tab
	 */
	public void enable(String tabPanelItem)
	{
		JSONArray params = new JSONArray() ;
		params.put(tabPanelItem) ;

		res.add(ResponseItem.createResponseItem(name,TABPANEL,ENABLE,params)) ;
	}
	
	/**
	 * Disable the tab
	 */
	public void disable(String tabPanelItem)
	{
		JSONArray params = new JSONArray() ;
		params.put(tabPanelItem) ;

		res.add(ResponseItem.createResponseItem(name,TABPANEL,DISABLE,params)) ;
	}
	
	/**
	 * Activate a tab
	 */
	public void activate(String tabPanelItem)
	{
		JSONArray params = new JSONArray() ;
		params.put(tabPanelItem) ;

		res.add(ResponseItem.createResponseItem(name,TABPANEL,ACTIVATE,params)) ;
	}
}
