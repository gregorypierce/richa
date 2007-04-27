package org.richa.runner;

import org.apache.commons.jelly.Script;

/**
 * This object will be cached by RichaScriptCache.
 * 
 * It is essentially a wrapper that stores the actual Script,
 * the full file path name and 
 * the timestamp of the underlying file on the filesystem
 *  
 * @author ram
 */
public class RichaPage
{
	//Timestamp
	private long lastmodified ;
	
	//Script
	private Script parsedscript ;
	
	/**
	 * Constructor
	 * @param fullpath
	 * @param lastmodified
	 * @param parsedscript
	 */
	public RichaPage(long lastmodified, Script parsedscript)
	{
		this.lastmodified = lastmodified ;
		this.parsedscript = parsedscript ;
	}

	public long getLastModified()
	{
		return lastmodified;
	}

	public void setLastModified(long lastmodified)
	{
		this.lastmodified = lastmodified;
	}

	public Script getParsedScript()
	{
		return parsedscript;
	}

	public void setParsedScript(Script parsedscript)
	{
		this.parsedscript = parsedscript;
	}
}
