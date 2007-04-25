package org.richa.runner;

import org.apache.commons.jelly.Script;
import org.richa.util.LRUCache;

/**
 * This class provides a cache of Script objects
 * @author ram
 *
 */
public class RichaScriptCache
{
	private static LRUCache<String,Script> scriptCache = new LRUCache<String,Script>(50) ;
	
	/**
	 * Get a pre-compiled script instance for a page if one exists
	 * @param pageurl
	 * @return Script object
	 */
	public static Script get(String pageurl)
	{
		if (pageurl != null)
			return ((Script)scriptCache.get(pageurl)) ;
		else
			return null ;
	}
	
	/**
	 * Store the pre-compiled script instance in the cache
	 * @param pageurl
	 * @param parsedScript
	 */
	public static void put(String pageurl, Script parsedScript)
	{
		if (pageurl != null && parsedScript != null)
			scriptCache.put(pageurl, parsedScript) ;
	}
}
