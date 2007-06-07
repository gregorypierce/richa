Richa.ExtJS = {}

Richa.ExtJS.Core =  
{
    processResponse : function(commands)
    {
	    var command ;
	
	    //Loop through all the commands from the server and process them
	    for (var loop = 0; loop <commands.length; loop++)
	    {
		    //Get the next command
		    command = commands[loop] ;
		
		    //Get the operation
		    var operation = command["operation"] ;
		
		    //Get the name
		    var name = command["name"] ;
		    
		    //Get the parameters
		    var params = command["params"] ;
		
		    //Build the id
		    var id = name + "-id" ;
		
		    //Get the element
		    var element = Ext.get(id) ;

		    //Get the tag name		
		    var tagName = element.dom.tagName.toLowerCase() ;

			var script ;
			
			if (params == null)
			    script = name + "." + operation + "();" ;
					
		    switch(tagName)
		    {
                case "input":
            	    //Get the type of the input commands
                    var type = element.dom.getAttribute("type") ;
                
                    //Process all input controls
                    Richa.ExtJS.Input.processCommand(id,element,operation,params,script) ;
                
                    break ;
                    
                case "fieldset":
            	    //Process all fieldset commands
                    Richa.ExtJS.FieldSet.processCommand(id, name,operation,params,script);
                
                    break ;
                    
                case "form":
            	    //Process all Form commands
                    Richa.ExtJS.Form.processCommand(operation,params);
                	
                	break ;
            }
	    }
    }
}	

Richa.ExtJS.Input = 
{
	processCommand : function(id,element,operation,params,script)
    {	
	    switch(operation)
	    {
			case "hide":
				Richa.ExtJS.Input.hide(id,element) ;
				break ;
			case "show":
				Richa.ExtJS.Input.show(id,element) ;	
				break ;
			case "enable":
				Richa.ExtJS.Input.enable(script) ;	
				break ;
			case "disable":
				Richa.ExtJS.Input.disable(script) ;	
				break ;
			case "focus":
				eval(script) ;
				break ;
			case "set":
				Richa.ExtJS.Input.set(id,element,params) ;
		}
	},
	
	set : function(id,element,params)
	{
		element.dom.value = params[0] ;
	},
	
	hide : function(id,element)
	{
		var domNode = Richa.ExtJS.Input.get(id,element) ;
		if (domNode != null)
			domNode.style.display = "none" ;
	},

	show : function(id,element)
	{
		var domNode = Richa.ExtJS.Input.get(id,element) ;
		if (domNode != null)
			domNode.style.display = "" ;
	},

	disable : function(script)
	{
		eval(script) ;
	},

	enable : function(script)
	{
		eval(script) ;
	},

	get : function(id,element)
	{
		var domNode, currElement;
		
		currElement = element ;
	
		var lblId = "lbl-" + id ;
		var lblElement = Ext.get(lblId) ;
		if (lblElement != null)		
			currElement = lblElement ;
							
		domNode = currElement.dom.parentNode ;
		if (domNode.tagName.toLowerCase() == "div")
		{
			var class = Ext.util.Format.trim(domNode.getAttribute("class")) ;
			if (class == "x-form-item" || class == "x-form-element")
				return domNode ;
		}
		
		return null ;
	}
}


Richa.ExtJS.FieldSet = 
{
	processCommand : function(id,name,operation,params,script)
	{	
		eval(script) ;
	
		switch(operation)
		{
			case "enable":
				Richa.ExtJS.FieldSet.enable(id,name,script) ;	
				break ;
			case "disable":
				Richa.ExtJS.FieldSet.disable(id,name,script) ;	
				break ;
		}
	},

	enable : function(id,name,script)
	{
		var stackstr = name + ".stack" ;
		var stack = eval(stackstr) ;
	    var slen = stack.length;
	    if(slen > 0)
	    {
	    	for(var i = 0; i < slen; i++) 
	    	{
	        	if(stack[i].isFormField)
	        	{
	            	var f = stack[i] ;
	            	var id = f.id ;
	            	var loc = id.lastIndexOf("-") ;
	            	if (loc != -1)
	            	{
	            		var fname = id.substr(0,loc) ;
	            		var tempscript = fname + ".enable();" ;
	            		Richa.ExtJS.Input.enable(tempscript) ;
	            	}
	            }
	        }
	    }
	},

	disable : function(id,name,script)
	{
		var stackstr = name + ".stack" ;
		var stack = eval(stackstr) ;
	    var slen = stack.length;
	    if(slen > 0)
	    {
	    	for(var i = 0; i < slen; i++) 
	    	{
	        	if(stack[i].isFormField)
	        	{
	            	var f = stack[i] ;
	            	var id = f.id ;
	            	var loc = id.lastIndexOf("-") ;
	            	if (loc != -1)
	            	{
	            		var fname = id.substr(0,loc) ;
	            		var tempscript = fname + ".disable();" ;
	            		Richa.ExtJS.Input.disable(tempscript) ;
	            	}
	            }
	        }
	    }
	}
}


Richa.ExtJS.Form = 
{
	processCommand : function(operation,params)
    {	
	    switch(operation)
	    {
			case "set":
				Richa.ExtJS.Form.set(params) ;
		}
	},
	
	set : function(params)
	{
		var fields = params[0] ;
		
		//Loop through all the fields
		for (var field in fields)
		{
			//Build the id
		    var id = field + "-id" ;
		
		    //Get the element
		    var element = Ext.get(id) ;
			if (element != null)
				element.dom.value = fields[field] ;
		}
	}
}