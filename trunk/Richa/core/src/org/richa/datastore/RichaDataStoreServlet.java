package org.richa.datastore;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.richa.event.EventListeners;
import org.richa.runner.Response;
import org.richa.util.ResponseUtils;
import org.richa.util.StoreUtils;
import org.richa.util.StringBufferWriter;

@SuppressWarnings("serial")
public class RichaDataStoreServlet extends HttpServlet
{
	private EventListeners listeners = null ;
	
	@Override
	public void init() throws ServletException
	{
		super.init();
		
		//Load all the event listeners and handlers in the application
		listeners = EventListeners.getInstance() ;
	}

	/**
	 * Handles event posts from the UI.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		//Process Request
		processRequest(request,response) ;
	}

	/**
	 * Handles event posts from the UI.
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		//Process Request
		processRequest(request,response) ;
	}

	/**
	 * Process a request from the client
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		PrintWriter pw = null;

		// Lookup a class to handle request. Use the event name which is passed
		// after the servlet name
		try
		{		
			// Get a print writer to the response
			pw = response.getWriter();

			// Events come into the servlet as <eventListner>/<eventHandler>
			String eventData = request.getPathInfo().substring(1);
			if (eventData != null)
			{
				String eventListener = eventData.substring(0, eventData
						.indexOf("/"));
				String dataStoreHandler = eventData.substring(eventData
						.indexOf("/") + 1);

				//Get an instance of the listener object
				Object listener = listeners.getEventListener(eventListener) ;
				
				//Read the data
				Map<String,String> params = readData(request) ;
				
				//Get the method object for the handler
				Method eventMethod = listeners.getDataStoreHandlerMethod(eventListener, dataStoreHandler) ;
				if (eventMethod != null)
				{
					//Create a list data store
					ListDataStore resultStore = new ListDataStore() ;
					
					//Setup the parameters
					Object[] methodParams = new Object[2] ;
					methodParams[0] = params ;
					methodParams[1] = resultStore ;
					
					//Invoke Handler
					eventMethod.invoke(listener,methodParams);
					
					//Get the data
					List results = resultStore.getData() ;
					
					//Translate the results in JSON
					sendResults(results,pw) ;
				}
				else
					ResponseUtils.sendResponse(pw, Response.FAIL, "Unable to load an data store handler method for listener: " + eventListener + " and handler: " + dataStoreHandler);
			}
			else
			{
				ResponseUtils.sendResponse(pw, Response.FAIL,  "No event listener or date store handler passed to the Richa Servlet");
			}
		}
		catch (Exception e)
		{
			ResponseUtils.sendResponse(pw, e) ;
		}		
		finally
		{
			if (pw != null)
				pw.close(); 
		}
	}

	private Map<String,String> readData(HttpServletRequest req) throws IOException
	{
		BufferedInputStream bis = new BufferedInputStream(req.getInputStream()) ;
		StringBufferWriter buffdata = new StringBufferWriter() ;
		
		//Read Buffer
		byte[] buff = new byte[4096];
		
		//Read the data from the servlet input stream
		while (-1 != bis.read(buff, 0, buff.length))
		{
			//Append it to the String Buffer
			buffdata.write(new String(buff)) ;
		}	
		
		String data = buffdata.getStringBuffer().toString() ;
		
		Map params = new HashMap<String,String>() ;
		String query = data ;
		if (query == null || query.equals(""))
			return params ;
			
		String[] pairs = query.split("&");
	    for (String pair : pairs) 
	    {
	    	String[] nameAndValue = pair.split("=");
	    	params.put(nameAndValue[0], nameAndValue[1]);
	    }
	    
	    return params ;
	}
	
	/**
	 * Send the results back to the client
	 */
	private void sendResults(List results, PrintWriter pw) throws JSONException, InvocationTargetException,IllegalAccessException
	{
		JSONObject output = new JSONObject() ;
		
		//Add the count
		output.putOpt("results", results.size()) ;
		
		//Add the fields
		JSONArray rows = StoreUtils.serializeListAsMap(results) ;
		
		output.put("rows", rows) ;
		
		System.out.println(output.toString()) ;
		
		//Send the output
		pw.print(output.toString()) ;
	}
}