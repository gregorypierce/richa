package org.richa.operations;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Operations that can be performed on a field
 * @author ram
 */
public interface Field
{
	/**
	 * Show a field
	 * @param name - Field Name
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment show(String name) ;
	
	/**
	 * Hide a field
	 * @param name - Field Name
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment hide(String name) ;
	
	/**
	 * Disable a field
	 * @param name - Field Name
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment disable(String name) ;
	
	/**
	 * Enable a field  
	 * @param name - Field Name
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment enable(String name) ;
	
	/**
	 * Set he field value
	 * @param name - Field Name
	 * @param value - Field Value
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment set(String name, String value) ;
	
	/**
	 * Set the field value
	 * @param name - Field Name
	 * @param value - Field Value
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment set(String name, int value) ;
	
	/**
	 * Set the field value
	 * @param name - Field Name
	 * @param value - Field Value
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment set(String name, long value) ;
	
	/**
	 * Set the field value
	 * @param name - Field Name
	 * @param value - Field Value
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment set(String name, float value) ;
	
	/**
	 * Set the field value
	 * @param name - Field Name
	 * @param value - Field Value
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment set(String name, double value) ;
	
	/**
	 * Set the field value
	 * @param name - Field Name
	 * @param value - Field Value
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment set(String name, Date value) ;
	
	/**
	 * Set the field value
	 * @param name - Field Name
	 * @param value - Field Value 
	 * @return JS Fragment that performs this operation
	 */
	public JSFragment set(String name, Timestamp value) ;	
}
