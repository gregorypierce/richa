package org.richa.databinding;

/**
 * This class represents a node in a treeview
 * @author ram
 *
 */
public class TreeNode
{
	//Text for the treenode
	private String text ;
	
	//Id for the treenode
	private String id ;
	
	//Class for the treenode
	private String cls ;
	
	//Is this a leaf node 
	private boolean leaf ;
	
	//Can this node be dropped
	private boolean droppable = false ;
	
	//Can this node have children
	private boolean canhavechildren = true ;

	/**
	 * Get the css class for the node
	 * @return
	 */
	public String getCls()
	{
		return cls;
	}

	/**
	 * Set the css class for the node
	 * @param cls
	 */
	public void setCls(String cls)
	{
		this.cls = cls;
	}

	/**
	 * Get the id for the node
	 * @return
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Set the id for the node
	 * @param id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Is this a leaf node
	 * @return
	 */
	public boolean isLeaf()
	{
		return leaf;
	}

	/**
	 * Set the type of the node
	 * @param leaf
	 */
	public void setLeaf(boolean leaf)
	{
		this.leaf = leaf;
	}

	/**
	 * Get the text of the node
	 * @return
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * Set the text of the node
	 * @param text
	 */
	public void setText(String text)
	{
		this.text = text;
	}

	/**
	 * Can the node have children
	 * @return
	 */
	public boolean canHaveChildren()
	{
		return canhavechildren;
	}

	/**
	 * Set if the node can have children
	 * @param canhavechildren
	 */
	public void setCanHaveChildren(boolean canhavechildren)
	{
		this.canhavechildren = canhavechildren;
	}

	/**
	 * Is the node droppable
	 * @return
	 */
	public boolean isDroppable()
	{
		return droppable;
	}

	/**
	 * Set if the node is droppable
	 * @param droppable
	 */
	public void setDroppable(boolean droppable)
	{
		this.droppable = droppable;
	}
}
