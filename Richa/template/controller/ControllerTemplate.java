package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.richa.annotations.DataStoreHandler;
import org.richa.annotations.EventHandler;
import org.richa.annotations.EventListener;
import org.richa.annotations.PageBindHandler;
import org.richa.event.EventContext;
import org.richa.event.EventResponse;

import model.*;

@EventListener("@controllerObjectName@Controller")
public class @controllerObjectName@Controller
{
	@EventHandler("@controllerObjectName@FormHandler")
	public void @controllerObjectName@FormHandler(EventContext context, EventResponse res)
	{

	}
	
	@DataStoreHandler("@controllerObjectName@DataStore")
	public List @controllerObjectName@DataStoreHandler(Map<String,String> params)
	{
		List @controllerObjectName@List = new LinkedList();
		

		
		return @controllerObjectName@List;	
	}
	
	@PageBindHandler()
	public Map @controllerObjectName@BindHandler()
	{
		Map dataMap = new HashMap() ;
		createDataMap(dataMap);
		
		
		return dataMap ;	
	}
	
	private void createDataMap(Map dataMap)
	{

	}
}
