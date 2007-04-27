package org.richa.resources;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.vfs.FileContent;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.VFS;

@SuppressWarnings("serial")
public class RichaResourcesServlet extends HttpServlet
{
	// File Object that represents the jar file
	FileObject jarFile;
	
	//Mime types
	MimetypesFileTypeMap mt = new MimetypesFileTypeMap() ;

	@Override
	public void init() throws ServletException
	{
		super.init();

		try
		{
			// Get the resource path for the extjs jar with the resources
			String resourcePath = getServletContext().getRealPath("/WEB-INF/lib/richa-extjs.jar");

			// Open the jar file
			jarFile = VFS.getManager().resolveFile("jar:" + resourcePath);			
		} 
		catch (FileSystemException eFSE)
		{
			eFSE.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		OutputStream out = null;

		try
		{
			// Get the output stream
			out = response.getOutputStream();

			// Get the path name of the resource
			String resource = request.getPathInfo();
			if (resource == null)
			{
				sendError(response, resource);
				return;
			}

			//Create a file selector for the appropriate resource name
			ResourceFileSelector fileSelector = new ResourceFileSelector(resource);

			// Search the jar file
			FileObject[] fo = jarFile.findFiles(fileSelector);

			// If we found a value
			if (fo.length > 0)
			{
				//Get the content
				FileContent content = fo[0].getContent();
				
				//Set the mime type
				response.setContentType(mt.getContentType(resource)) ;
				
				//Set the content length
				response.setContentLength((int)content.getSize()) ;
				
				//Send the data
				sendFile(content, out) ;
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

	@Override
	public void destroy()
	{
		try
		{
			// Close the jar file object and filesystem manager
			jarFile.close();
		} catch (FileSystemException eFSE)
		{
			eFSE.printStackTrace();
		}

		super.destroy();
	}

	/**
	 * Send the file to the browser
	 * @param content
	 * @param out
	 */
	private void sendFile(FileContent content, OutputStream out)
	{
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try
		{
			// Use Buffered Stream for reading/writing.
			InputStream in = content.getInputStream();
			bis = new BufferedInputStream(in);
			
			bos = new BufferedOutputStream(out);
			
			byte[] buff = new byte[4096];
			
			int bytesRead;
			
			//Read the file
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
			{
				bos.write(buff, 0, bytesRead);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		finally
		{
			try
			{
				if (bis != null)
					bis.close();
				
				if (bos != null)
					bos.close();
			}
			catch (Exception e)
			{
				e.printStackTrace() ;
			}
		}
	}
}