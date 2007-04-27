package org.richa.runner;

import java.io.File;

import org.apache.commons.jelly.Script;
import org.richa.util.LRUCache;



/**
 * This class provides a cache of Script objects
 * @author ram
 *
 */
public class RichaPageCache
{
	private static LRUCache<String,RichaPage> scriptCache = new LRUCache<String,RichaPage>(50) ;
	
	/**
	 * Get a pre-compiled script instance for a page if one exists
	 * @param pagename
	 * @return Script object
	 */
	public static Script get(String pagename)
	{		
		if (pagename != null)
		{
			RichaPage script =  ((RichaPage)scriptCache.get(pagename)) ;
			if (script != null)
			{
				//Get the currenttimestamp and the stored timestamp
				long curtimestamp = getLastModified(pagename) ;
				long storedtimestamp = script.getLastModified() ;

				//If the current timestamp is not newer than the stored timestamp
				if (storedtimestamp >= curtimestamp)
					return (script.getParsedScript())  ;
			}
		}
		
		return null ;
	}
	
	/**
	 * Store the pre-compiled script instance in the cache
	 * @param pagename
	 * @param parsedScript
	 */
	public static void put(String pagename, Script parsedscript)
	{
		if (pagename != null && parsedscript != null)
		{	
			long lastmodified = getLastModified(pagename) ;
			
			//Create a script object
			RichaPage script = new RichaPage(lastmodified,parsedscript) ;
			
			//Store it in the cache
			scriptCache.put(pagename, script) ;
		}
	}
	
	/**
	 * Get the last modified timestamp of a page
	 * @param pagename
	 * @return
	 */
	private static long getLastModified(String pagename)
	{
		//Build the full file name
		String fullpath = RichaRunner.getRootPath() + pagename ;
		
		//Open the file and get the timestamp
		File pagefile = new File(fullpath) ;
		if (pagefile != null)
			return (pagefile.lastModified()) ;
		else
			return (long) -1 ;
	}
}
