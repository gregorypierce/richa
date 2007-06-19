package test.eventlisteners;

import org.richa.annotations.EventHandler;
import org.richa.annotations.EventListener;
import org.richa.event.EventContext;
import org.richa.event.EventResponse;

@EventListener("tablistener")
public class TestTabPanelListener
{	
	@EventHandler()
	public void hideTab(EventContext context, EventResponse res)
	{
		res.getTabPanel("customerTabs").hide("address") ;
	}
	
	@EventHandler()
	public void showTab(EventContext context, EventResponse res)
	{
		res.getTabPanel("customerTabs").show("address") ;
	}
	
	@EventHandler()
	public void enableTab(EventContext context, EventResponse res)
	{
		res.getTabPanel("customerTabs").enable("address") ;
	}
	
	@EventHandler()
	public void disableTab(EventContext context, EventResponse res)
	{
		res.getTabPanel("customerTabs").disable("address") ;
	}
	
	@EventHandler()
	public void activateTab(EventContext context, EventResponse res)
	{
		res.getTabPanel("customerTabs").activate("address") ;
	}
}
