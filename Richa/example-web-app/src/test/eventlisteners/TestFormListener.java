package test.eventlisteners;

import java.util.Date;
import java.util.Map;

import org.richa.annotations.EventHandler;
import org.richa.annotations.EventListener;
import org.richa.annotations.ListDataStoreHandler;
import org.richa.annotations.PageBindHandler;
import org.richa.databinding.BindingContext;
import org.richa.datastore.ListDataStore;
import org.richa.event.EventContext;
import org.richa.event.EventResponse;

import test.entities.Customer;
import test.entities.State;

@EventListener("formListener")
public class TestFormListener
{
	@EventHandler
	public void setFields(EventContext context, EventResponse res)
	{
		res.getField("name").set("Ram Venkataraman");
		res.getField("dob").set(new Date());
		
		Customer cust = new Customer() ;
		cust.setId(5) ;
		cust.setAge(37) ;
		cust.setName("Ram Venkataraman") ;
		cust.setAddress1("6610 Bradford Place") ;
		cust.setAddress2("") ;
		cust.setCity("Cumming") ;
		cust.setState("Georgia") ;
		cust.setZip(30040) ;
		cust.setComments("Good Customer") ;
		cust.setDob(new Date()) ;
		cust.setRender(false) ;
		
		res.getForm("customer").set(cust) ;
		
	}
	
	@EventHandler
	public void hideFields(EventContext context, EventResponse res)
	{
		res.getField("name").hide() ;
		res.getField("dob").hide();
		
	}
	
	@EventHandler
	public void showFields(EventContext context, EventResponse res)
	{
		res.getField("name").show() ;
		res.getField("dob").show();
	}
	
	@EventHandler
	public void enableFields(EventContext context, EventResponse res)
	{
		res.getField("name").enable() ;
		res.getField("dob").enable();
		res.getField("male").enable();
		res.getField("dob").focus() ;
	}
	
	@EventHandler
	public void disableFields(EventContext context, EventResponse res)
	{
		res.getField("name").disable() ;
		res.getField("dob").disable();
		res.getField("male").disable();
	}
	
	@EventHandler
	public void hideFS(EventContext context, EventResponse res)
	{
		res.getFieldSet("legaddr").hide();
	}
	
	@EventHandler
	public void showFS(EventContext context, EventResponse res)
	{
		res.getFieldSet("legaddr").show();
	}
	
	@EventHandler
	public void enableFS(EventContext context, EventResponse res)
	{
		res.getFieldSet("legaddr").enable();
	}
	
	@EventHandler
	public void disableFS(EventContext context, EventResponse res)
	{
		res.getFieldSet("legaddr").disable();
	}
	
	@EventHandler
	public void collapsePanel(EventContext context, EventResponse res)
	{
		res.getBorderLayout("laycustomer").collapse("east");
	}
	
	@EventHandler
	public void expandPanel(EventContext context, EventResponse res)
	{
		res.getBorderLayout("laycustomer").expand("east");
	}
	
	@EventHandler
	public void hidePanel(EventContext context, EventResponse res)
	{
		res.getBorderLayout("laycustomer").hide("east");
	}
	
	@EventHandler
	public void showPanel(EventContext context, EventResponse res)
	{
		res.getBorderLayout("laycustomer").show("east");
	}
	
	@ListDataStoreHandler("statelist")
	public void testDataStoreHandler(Map<String,String> params, ListDataStore data)
	{
		String query = (String) params.get("query").trim() ;
		
		query = query.toLowerCase() ;
		if (query.startsWith("g") || query.equals(""))			
			data.add(new State("GA", "Georgia")) ;
		
		if (query.startsWith("c")|| query.equals(""))
			data.add(new State("CA", "California")) ;
		
		if (query.startsWith("f")|| query.equals(""))
			data.add(new State("FL", "Florida")) ;
		
		if (query.startsWith("a")|| query.equals(""))
		{
			data.add(new State("AL", "Alabama")) ;
			data.add(new State("AZ", "Arizona")) ;
			data.add(new State("AK", "Alaska")) ;
		}
		
		if (query.startsWith("n")|| query.equals(""))
			data.add(new State("NY", "New York")) ;
	}
	
	@PageBindHandler()
	public void testBindHandler(BindingContext context)
	{
		Customer cust1 = new Customer() ;
		cust1.setId(1) ;
		cust1.setAge(12) ;
		cust1.setName("Name 1") ;
		cust1.setAddress1("Address 11") ;
		cust1.setAddress2("Address 21") ;
		cust1.setCity("City 1") ;
		cust1.setState("State 1") ;
		cust1.setZip(1) ;
		cust1.setComments("This is a test1") ;
		cust1.setDob(new Date()) ;
		cust1.setRender(false) ;
		
		Customer cust2 = new Customer() ;
		cust2.setId(2) ;
		cust2.setAge(21) ;
		cust2.setName("Name 2") ;
		cust2.setAddress1("Address 12") ;
		cust2.setAddress2("Address 22") ;
		cust2.setCity("City 2") ;
		cust2.setState("State 2") ;
		cust2.setZip(2) ;
		cust2.setComments("This is a test2") ;
		cust2.setDob(new Date()) ;
		
		context.add("cust1", cust1) ;
		context.add("cust2", cust2) ;	
	}
}

