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
	public void show(String name) ;
	
	/**
	 * Hide the tab
	 * @param name - Tab Name
	 * @return JS Fragment that performs this operation
	 */
	public void hide(String name) ;
	
	/**
	 * Disable the tab
	 * @param name - Tab Name
	 * @return JS Fragment that performs this operation
	 */
	public void disable(String name) ;
	
	/**
	 * Enable the tab
	 * @param name - Tab Name
	 * @return JS Fragment that performs this operation
	 */
	public void enable(String name) ;
	
	/**
	 * Add a tab item
	 * @param name - Tab Item Name
	 * @return JS Fragment that performs this operation
	 */
	public void addItem(String name) ;
	
}
