package org.richa.tags.extjs.form;

import org.richa.tags.extjs.BaseExtJSTag;

/**
 * Base Class for all the field controls
 */

public abstract class BaseControlTag extends BaseExtJSTag
{
    /**
     * Serialize the creation of the the tag and its attributes
     */
    protected void serializeCreation()
    {   
	    scriptBuffer.appendln("    var " + getName() + " =  new " + getObjectName() + "({");
    	
	    //Serialize the attributes
    	serializeAttributes() ;
    	
    	scriptBuffer.appendln("    });") ;
    	
    	//Add it to thecurrent container
    	scriptBuffer.appendln("    " + getCurrentFormName() + ".add(" + getName() + ");") ;
    }    
    
    /**
     * Serialize the creation of the the tag and its attributes
     */
    protected void serializeCreation(String data)
    {   
	    scriptBuffer.appendln("    var " + getName() + " =  new " + getObjectName() + "(" + data +  ",{");
    	
	    //Serialize the attributes
    	serializeAttributes() ;
    	
    	scriptBuffer.appendln("    });") ;
    	
    	//Add it to thecurrent container
    	scriptBuffer.appendln("    " + getCurrentFormName() + ".add(" + getName() + ");") ;
    }   

    /**
     * Serialize the apply to creation of the the tag and its attributes
     */
    protected void serializeApplyToCreation()
    {
    	scriptBuffer.appendln("    var " + getName() + " = new " + getObjectName() + "({");
    	
	    //Serialize the attributes
    	serializeAttributes() ;
    	
    	scriptBuffer.appendln("    });") ;
    	
    	//Append the applyTo method call
    	scriptBuffer.appendln(getName() + ".applyTo('" + getAttribute("applyTo") + "');") ;
    }    

}
