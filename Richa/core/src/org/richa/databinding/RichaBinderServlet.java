package org.richa.databinding;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class RichaBinderServlet extends HttpServlet
{
	@Override
	public void init() throws ServletException
	{
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = null;

		try
		{
			// Get the output stream
			out = response.getWriter() ;
			
			// Get the path name of the resource
			String resource = request.getPathInfo();
			if (resource == null)
			{
				sendError(response, resource);
				return;
			}
			
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		finally
		{
			if (out != null)
			{
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * Send not found error to the browser
	 * 
	 * @param response
	 * @param template
	 */
	private void sendError(HttpServletResponse response, String template)
			throws IOException
	{
		response.sendError(HttpServletResponse.SC_NOT_FOUND, template);
		return;
	}
}