package org.richa.tags.extjs.containers;

import org.richa.tags.extjs.BaseExtJSTag;

/**
 * Base Class for all container controls
 */

public abstract class BaseContainerTag extends BaseExtJSTag
{	
	/**
	 * Serialize a child creation 
	 */
	protected void serializeChild()
	{	
		//Get the current form name
		String parent = getCurrentFormName() ;
		
		scriptBuffer.appendln("    " + getName() + " = " + parent + "." + getObjectName() + "({");
		
		//Serialize all the attributes
		serializeAttributes() ;
		
		scriptBuffer.appendln("    });") ; 
	} 

}
