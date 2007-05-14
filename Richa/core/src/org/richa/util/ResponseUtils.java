package org.richa.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;

import org.richa.runner.Response;

/**
 * Provides methods to send a response back to the browser
 * @author ram
 *
 */
public class ResponseUtils
{
	/**
	 * Send a response
	 */
	public static void sendResponse(PrintWriter pw, String code, String data)
	{
		try
		{
			//Create a response object
			Response res = new Response() ;
			res.setCode(code) ;
			res.setData(data) ;
			
			//Send the data
			pw.write(res.serialize()) ;
		}
		catch (Exception exp)
		{
			exp.printStackTrace() ;
		}
	}
	
	/**
	 * Send an exception to the browser
	 */
	public static void sendResponse(PrintWriter pwResponse, Exception e)
	{
		StringWriter sw =  new StringWriter() ;
		PrintWriter pw = new PrintWriter(sw) ;
		
		//Write the exception into the PrintWriter
		e.printStackTrace(pw) ;
		
		//Send the response
		sendResponse(pwResponse,Response.FAIL, sw.toString()) ;
	}
}
