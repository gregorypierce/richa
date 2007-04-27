package org.richa.runner ;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.richa.util.AppendingStringBuffer;

@SuppressWarnings("serial")
public class RichaRunnerServlet extends HttpServlet
{
	@Override
	public void init() throws ServletException
	{
		super.init();
			
		//Get the root path for the context
		String rootpath = getServletContext().getRealPath("/") ;
		
		//Strip out the last /
		rootpath = rootpath.substring(0,rootpath.length() - 1) ;
		
		//Set the path in Richa Runner
		RichaRunner.setRootPath(rootpath) ;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter pw = null ;
		
		try
		{
			//Get a print writer      
			pw = response.getWriter() ;
			
			//Get the page name
			String pagename = request.getServletPath() ;
			if (pagename == null)
			{
				sendError(response,pagename);
				return ;
			}
						
			//Get the name of the context
			String context = request.getContextPath() ;
			if (context == null)
			{ 
				sendError(response,pagename);
				return ;
			}
			
			//Get the web context
			RichaRunner runner = new RichaRunner() ;
			runner.setWebContext(context) ;
			runner.setServletContext(getServletContext()) ;
			runner.setPageName(pagename) ;
			
			//Invoke the page runner
			AppendingStringBuffer output = runner.runPage() ;
			
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