package org.richa.databinding;

import java.util.Collection;

import org.json.JSONArray;

/**
 * This class provides the m
 * @author ram
 *
 */
public abstract class JSONDataBinder
{
	/**
	 * Return JSON String
	 * @return
	 */
	public String getData(Collection data) 
	{
		JSONArray jsonArray = null ;  
		
		return jsonArray.toString() ;
	}
}
