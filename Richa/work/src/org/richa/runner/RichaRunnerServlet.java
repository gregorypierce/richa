package org.richa.runner ;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.richa.util.AppendingStringBuffer;

public class RichaRunnerServlet extends HttpServlet
{
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