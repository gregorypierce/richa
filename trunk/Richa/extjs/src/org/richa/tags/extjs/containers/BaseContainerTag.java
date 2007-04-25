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
	protected void serializeChildCreation()
	{	
		//Get the current form name
		String parent = getCurrentFormName() ;
		
		scriptBuffer.appendln("    " + parent + "." + getObjectName() + "({");
		
		//Serialize all the attributes
		serializeAttributes() ;
		
		scriptBuffer.appendln("    });") ; 
	} 

}

