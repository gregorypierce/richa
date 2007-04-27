package org.richa.event;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.richa.operations.BorderLayout;
import org.richa.operations.Field;
import org.richa.operations.Form;
import org.richa.operations.Grid;
import org.richa.operations.JSFragment;
import org.richa.operations.Menu;
import org.richa.operations.Tab;
import org.richa.operations.Toolbar;
import org.richa.operations.Tree;
import org.richa.util.AppendingStringBuffer;

/**
 * EventResponse object that is returned by the event handler 
 * @author ram
 *
 */
public abstract class EventResponse
{
	//Form operations  
	public static Form form ;
	
	//Field operations  
	public static Field field ;
	
	//Grid operations  
	public static Grid grid ;
	
	//Tab operations  
	public static Tab tab ;
	
	//Toolbar operations  
	public static Toolbar toolbar ;
	
	//Menu operations  
	public static Menu menu ;
	
	//Tree operations  
	public static Tree tree ;
	
	
	//Border Layout  
	public static BorderLayout borderlayout ;
	
	
	//Response Fragments
	private Map<String,JSFragment> responses = new HashMap<String,JSFragment>() ;
	
	/**
	 * Add a new operation
	 * @param fragment
	 */
	public void add(JSFragment fragment)
	{
		responses.put(fragment.getOperation(),fragment) ;
	}
	
	/**
	 * Remove an operation
	 * @param operation
	 */
	public void remove(String operation )
	{
		responses.remove(operation) ;
	}
	
	/**
	 * Clear all the operations
	 */
	public void clearOperations()
	{
		responses.clear() ;
	}
	
	/**
	 * Serialize the operations
	 * @return 
	 */
	public String serialize()
	{
		//Script buffer
		AppendingStringBuffer script = new AppendingStringBuffer() ;
		
		//Get the iterator for the keys
		Iterator iter = responses.keySet().iterator() ;
		String operation ;
		
		//Loop through all the keys
		while (iter.hasNext())
		{
			//Get the key
			operation = (String) iter.next() ;
			
			//Get the fragment
			JSFragment fragment = responses.get(operation) ;
			
			//Append it to the buffer
			script.append(fragment.getFragment()) ;
		}
		
		//Send it to the browser
		return script.toString() ;
	}
}
