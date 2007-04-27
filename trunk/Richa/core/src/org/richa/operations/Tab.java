package org.richa.operations;

/**
 * Operations that can be performed on a tab
 * @author ram
 *
 */
public interface Tab 
{
	/**
	 * Show the tab
	 * @param name - Tab Name
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment show(String name) ;
	
	/**
	 * Hide the tab
	 * @param name - Tab Name
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment hide(String name) ;
	
	/**
	 * Disable the tab
	 * @param name - Tab Name
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment disable(String name) ;
	
	/**
	 * Enable the tab
	 * @param name - Tab Name
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment enable(String name) ;
	
	/**
	 * Add a tab item
	 * @param name - Tab Item Name
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment addItem(String name) ;
	
}
