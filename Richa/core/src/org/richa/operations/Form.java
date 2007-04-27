package org.richa.operations;

/**
 * Operations that can be performed on a form
 * @author ram
 *
 */
public interface Form 
{
	/**
	 * Show the form
	 * @param name - Form Name
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment show(String name) ;
	
	/**
	 * Hide the form
	 * @param name - Form Name
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment hide(String name) ;
	
	/**
	 * Disable the form
	 * @param name - Form Name
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment disable(String name) ;
	
	/**
	 * Enable the form
	 * @param name - Form Name
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment enable(String name) ;
	
	/**
	 * Bind object to form
	 * @param name - Form Name
	 * @param value - Object
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment set(String name, Object value) ;
}
