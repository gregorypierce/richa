package org.richa.event;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.richa.runner.Response;
import org.richa.util.ResponseUtils;
import org.richa.util.StringBufferWriter;

@SuppressWarnings("serial")
public class RichaEventServlet extends HttpServlet
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
				String eventHandler = eventData.substring(eventData
						.indexOf("/") + 1);

				//Generate an Event context for all the posted data
				EventContext context = getContext(request) ;
				context.print() ;					
			
				//Get an instance of the listener object
				Object listener = listeners.getEventListener(eventListener) ;
				
				//Get the method object for the handler
				Method eventMethod = listeners.getEventHandlerMethod(eventListener, eventHandler) ;
				if (eventMethod != null)
				{
					//Create a new EventResponse
					EventResponse res = new EventResponse() ;
					
					Object[] methodParams = new Object[2] ;
					methodParams[0] = context ;
					methodParams[1] = res ;
					
					//Invoke it
					eventMethod.invoke(listener,methodParams);
					
					pw.write(res.serialize()) ;
					
				}
				else
					ResponseUtils.sendResponse(pw, Response.FAIL, "Unable to load an event handler method for listener: " + eventListener + " and handler: " + eventHandler);
			}
			else
			{
				ResponseUtils.sendResponse(pw, Response.FAIL,  "No event listener or handler passed to the Richa Servlet");
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
	
	/**
	 * Read the JSON posted data and convert it into an EventContext object
	 * @param req
	 * @throws IOException
	 */
	private EventContext getContext(HttpServletRequest req) throws IOException, JSONException
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
		
		//Parse the JSON object
		JSONObject jsondata = new JSONObject(buffdata.getStringBuffer().toString()) ;
		
		//Get the names of the parsed object
		JSONArray names = jsondata.names() ;
		
		//Create a new context object
		EventContext context = new EventContext() ;
		
		//Loop through all the objects
		for (int i = 0; i < names.length(); i++)
		{
			String name = (String)names.get(i) ;
			String value = (String)jsondata.get(name) ;
			value = URLDecoder.decode(value,"UTF-8") ;
			
			//Add to the context
			context.add(name, value) ;
		}
		
		return context ;
	}
}