package org.richa.runner;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.richa.util.AppendingStringBuffer;
import org.richa.util.ResponseUtils;

@SuppressWarnings("serial")
public class RichaRunnerServlet extends HttpServlet
{
	
	@Override
	public void init() throws ServletException
	{
		super.init();

		// Get the root path for the context
		String rootpath = getServletContext().getRealPath("/");

		// Strip out the last /
		rootpath = rootpath.substring(0, rootpath.length() - 1);

		// Set the path in Richa Runner
		RichaRunner.setRootPath(rootpath);
		
		// load the persistence unit from the context
		/*String persistenceUnitName = getServletContext().getInitParameter("persistenceUnit");
	 	entityManagerFactory = Persistence.createEntityManagerFactory( persistenceUnitName );
		entityManager = entityManagerFactory.createEntityManager();*/
	}
	
	@Override
	public void destroy() 
	{
		super.destroy();
		
		/*entityManager.close();
		entityManagerFactory.close();*/
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter pw = null;

		try
		{
			// Get a print writer to the response
			pw = response.getWriter();

			// Get the page name
			String pagename = request.getServletPath();
			if (pagename == null)
			{
				ResponseUtils.sendResponse(pw, Response.FAIL, "Page Not Found:" + pagename);
				return;
			}

			// Get the name of the context
			String context = request.getContextPath();
			if (context == null)
			{
				ResponseUtils.sendResponse(pw, Response.FAIL, "Page Not Found:" + pagename);
				return;
			}

			// Get the web context
			RichaRunner runner = new RichaRunner();
			runner.setWebContext(context);
			runner.setServletContext(getServletContext());
			runner.setPageName(pagename);

			// Invoke the page runner
			AppendingStringBuffer output = runner.runPage();
			
			// Send the output
			if (output != null)
				pw.print(output.getValue());
			else
				System.out.println("Hello") ;

		}
		catch (Exception e)
		{
			ResponseUtils.sendResponse(pw, e) ;
		}
		finally
		{
			// Close the writer
			if (pw != null)
				pw.close();
		}
	}
}