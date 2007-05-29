package test.eventhandlers;

import java.util.Date;
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

import test.entities.Customer;
import test.entities.State;

@EventListener("formListener")
public class TestFormHandler
{
	@EventHandler("formHandler")
	public void testFormHandler(EventContext context, EventResponse res)
	{
		res.getField("name").hide() ;
		res.getField("dob").hide();
	}
	
	@DataStoreHandler("statelist")
	public List testDataStoreHandler(Map<String,String> params)
	{
		String query = (String) params.get("query").trim() ;
		
		System.out.println( "Query: [" + query + "]" );
		
		List statelist = new LinkedList() ;
		
		query = query.toLowerCase() ;
		if (query.startsWith("g") || query.equals(""))			
			statelist.add(new State("GA", "Georgia")) ;
		
		if (query.startsWith("c")|| query.equals(""))
			statelist.add(new State("CA", "California")) ;
		
		if (query.startsWith("f")|| query.equals(""))
			statelist.add(new State("FL", "Florida")) ;
		
		if (query.startsWith("a")|| query.equals(""))
		{
			statelist.add(new State("AL", "Alabama")) ;
			statelist.add(new State("AZ", "Arizona")) ;
			statelist.add(new State("AK", "Alaska")) ;
		}
		
		if (query.startsWith("n")|| query.equals(""))
			statelist.add(new State("NY", "New York")) ;
		
		return statelist ;	
	}
	
	@PageBindHandler()
	public Map testBindHandler()
	{
		Map test = new HashMap() ;
		createTestData(test);
		
		
		return test ;	
	}
	
	private void createTestData(Map test)
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
		
		test.put("cust1", cust1) ;
		test.put("cust2", cust2) ;
	}
}

