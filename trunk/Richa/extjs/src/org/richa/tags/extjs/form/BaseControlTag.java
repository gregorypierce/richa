package org.richa.tags.extjs.form;

import org.richa.tags.extjs.BaseExtJSTag;

/**
 * Base Class for all the field controls
 */

public abstract class BaseControlTag extends BaseExtJSTag
{
    /**
     * Serialize the creation of the the tag and its attributes
     * If the add parameter is set to true, the object is also added to the active container
     * This method allows the object to be initialized
     */
    protected void serialize(String init, boolean add)
    {   
	    scriptBuffer.appendln("    " + getName() + " =  new " + getObjectName() + "(" + init +  ",{");
    	
	    //Serialize the attributes
    	serializeAttributes() ;
    	
    	scriptBuffer.appendln("    });") ;
    	
    	//Add it to thecurrent container
    	if (add)
    		scriptBuffer.appendln("    " + getCurrentFormName() + ".add(" + getName() + ");") ;
    }   

    /**
     * Serialize the creation of the the tag and its attributes
     */
    protected void serialize(boolean add)
    {   
	    scriptBuffer.appendln("    " + getName() + " =  new " + getObjectName() + "({");
    	
	    //Serialize the attributes
    	serializeAttributes() ;
    	
    	scriptBuffer.appendln("    });") ;
    	
    	//Add it to thecurrent container
    	if (add)
    		scriptBuffer.appendln("    " + getCurrentFormName() + ".add(" + getName() + ");") ;
    }    
    
}