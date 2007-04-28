package org.richa.metadata;

/**
 * Container class for UI Meta Data
 * 
 * This class retains the name and handler name for page and form containers.
 * 
 * @author cdelashmutt
 */
public class ContainerMetaData
{
	private String name;
	private String listener;

	/**
	 * Creates a meta data object with the specified name and listner name
	 * @param name The name of the container
	 * @param listener The name of the listener for this container
	 */
	public ContainerMetaData(String name, String listener)
	{
		this.name = name;
		this.listener = listener;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getListener()
	{
		return listener;
	}

	public void setListener(String listener)
	{
		this.listener = listener;
	}


}
