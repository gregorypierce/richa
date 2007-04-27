package org.richa.operations;

public class JSFragment
{
	private StringBuffer fragment ;
	
	private String operation ;
	
	/**
	 * Create a JSFragment object
	 * @param fragment
	 * @param operationname
	 */
	public JSFragment(StringBuffer fragment, String operation)
	{
		this.fragment = fragment ;
		this.operation = operation ;
	}
	
	/**
	 * Get the javascript fragment
	 * @return
	 */
	public StringBuffer getFragment()
	{
		return fragment ;
	}
	
	/**
	 * Set the javascript fragment
	 * @param fragment
	 */
	public void setFragment(StringBuffer fragment)
	{
		this.fragment = fragment ;
	}
	
	/**
	 * Get the operation
	 * @return
	 */
	public String getOperation()
	{
		return operation ;
	}
	
	/**
	 * Set the operation 
	 * @param operation 
	 */
	public void setOperation(String operation)
	{
		this.operation = operation ;
	}
}