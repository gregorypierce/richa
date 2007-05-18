package org.richa.tags.extjs.helper ;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.tags.extjs.BaseExtJSTag;
import org.richa.util.AppendingStringBuffer;
import org.richa.util.JSMin;
import org.richa.util.JSMin.UnterminatedCommentException;
import org.richa.util.JSMin.UnterminatedRegExpLiteralException;
import org.richa.util.JSMin.UnterminatedStringLiteralException;
import org.xml.sax.SAXException;

/**
 * This class represents a Page tag. There should be only page tag in one page
 * @author ram
 *
 */
public class Page extends BaseExtJSTag
{
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		//Initialize the script
		startScript() ;
	}
	
	/**
	 * Generate html and js after the body is processed
	 */
	protected void afterBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		//End the script
		endScript() ;
		
		//Write to the output stream
		output.write(scriptBuffer.toString()) ;
	}
	
	private void startScript()
	{
		scriptBuffer.appendln("<script>") ;
		scriptBuffer.appendln("Ext.onReady(function(){") ;
	}
	
	private void endScript()
	{	
		scriptBuffer.appendln("})") ;
		scriptBuffer.appendln("</script>");
	}
	
	private String jsMin(AppendingStringBuffer scriptBuffer)
	{
		StringReader input = new StringReader(scriptBuffer.toString());
		StringWriter output = new StringWriter() ;
		
		try
		{ 	
			JSMin jsmin = new JSMin(input, output);
			jsmin.jsmin();
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace(new PrintWriter(output));
		}
		catch (IOException e)
		{
			e.printStackTrace(new PrintWriter(output));
		}
		catch (UnterminatedRegExpLiteralException e)
		{
			e.printStackTrace(new PrintWriter(output));
		}
		catch (UnterminatedCommentException e)
		{
			e.printStackTrace(new PrintWriter(output));
		}
		catch (UnterminatedStringLiteralException e)
		{
			e.printStackTrace(new PrintWriter(output));
		}
		
		return output.toString() ;
	}
}

