package org.richa.commands;

import org.richa.event.EventResponse;

/**
 * Operations that can be performed on a tab panel
 * @author ram
 *
 */
public class TabPanel extends BaseCommand 
{
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
	public void show() 
	{
		res.add(ResponseItem.createResponseItem(name,HIDE)) ;
	}
	
	/**
	 * Hide the tab
	 */
	public void hide(String name)
	{
		res.add(ResponseItem.createResponseItem(name,HIDE)) ;
	}
}
