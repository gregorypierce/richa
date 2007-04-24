package org.richa.runner ;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.richa.event.EventListenerMetaData;
import org.richa.scanner.EventListenerScanner;
import org.richa.util.AppendingStringBuffer;

@SuppressWarnings("serial")
public class RichaRunnerServlet extends HttpServlet
{

	private Map<String,EventListenerMetaData>eventListeners; 

	@Override
	public void init() throws ServletException
	{
		super.init();
		
		//Find event handlers
		eventListeners = new EventListenerScanner("simpli.xml").getClasses();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter pw = null ;
		
		try
		{
			//Get a print writer      
			pw = response.getWriter() ;
			
			//Get the path name of the template
			String template = request.getServletPath() ;
			if (template == null)
			{
				sendError(response,template);
				return ;
			}
			
			//Get a URL to the resources
			URL pageurl = getServletContext().getResource(template) ;
			if (pageurl == null)
			{
				sendError(response,template);
				return ;
			}
			
			//Get the name of the context
			String context = request.getContextPath() ;
			if (context == null)
			{ 
				sendError(response,template);
				return ;
			}
			
			//Invoke the page runner
			AppendingStringBuffer output = RichaRunner.runPage(pageurl,context) ;
			
			//Send the output
			pw.print(output.getValue()) ;	
			
		}
		catch (Exception e)
		{
			//Write the stacktrace to the page
			e.printStackTrace(pw) ;
		}
		finally
		{
			//Close the writer
			if (pw != null)
				pw.close() ;
		}
	}
	
	/**
	 * Handles event posts from the UI.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//Lookup a class to handle request. Use the event name which is passed after the servlet name
		try
		{
			//Events come into the servlet as <eventListner>/<eventHandler>
			String eventData = request.getPathInfo().substring(1);
			String eventListner = eventData.substring(0,eventData.indexOf("/"));
			String eventHandler = eventData.substring(eventData.indexOf("/")+1);
			
			Class eventListenerClass = eventListeners.get(eventListner).getEventListener();
			
			Object eventListener = eventListenerClass.newInstance();
			
			//Find the method that is associated with the eventName
			Method eventMethod = eventListeners.get(eventListner).getEventHandler(eventHandler);
			
			response.getWriter().println(eventMethod.invoke(eventListener));
		}
		catch(Throwable e)
		{
			throw new ServletException(e);
		}
		
	}
	
	/**
	 * Send not found error to the browser
	 * @param response
	 * @param template
	 */
	private void sendError(HttpServletResponse response, String template) throws IOException
	{
		response.sendError(HttpServletResponse.SC_NOT_FOUND,template) ;
		return ;
	}
}