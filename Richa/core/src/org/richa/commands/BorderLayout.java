package org.richa.commands;

import org.json.JSONArray;
import org.richa.event.EventResponse;

public class BorderLayout extends BaseCommand
{
	private static final String LAYOUT = "layout" ;

	/**
	 * Constructor
	 */
	public BorderLayout(String name, EventResponse res)
	{
		super(name,res) ;
	}
	
	/**
	 * Expand a region in the layout
	 */
	public void expand(String region)
	{
		JSONArray params = new JSONArray() ;
		params.put(region) ;
		
		res.add(ResponseItem.createResponseItem(name,LAYOUT,EXPAND,params)) ;	
	}
	
	/**
	 * Collapse a region in the layout
	 */
	public void collapse(String region)
	{
		JSONArray params = new JSONArray() ;
		params.put(region) ;
		
		res.add(ResponseItem.createResponseItem(name,LAYOUT,COLLAPSE,params)) ;
	}
	
	
	/**
	 * Hide a region in the layout
	 */
	public void hide(String region)
	{
		JSONArray params = new JSONArray() ;
		params.put(region) ;
		
		res.add(ResponseItem.createResponseItem(name,LAYOUT,HIDE,params)) ;	
	}
	
	/**
	 * Show a region in the layout
	 */
	public void show(String region)
	{
		JSONArray params = new JSONArray() ;
		params.put(region) ;
		
		res.add(ResponseItem.createResponseItem(name,LAYOUT,SHOW,params)) ;
	}
	
}
