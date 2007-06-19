/***************************************
* ExtJS specific objects
* Author: Ram
*/
Richa.ExtJS = {}

Richa.ExtJS.Core =  
{
    processResponse : function(commands)
    {
	    var command,script ;
	
	    //Loop through all the commands from the server and process them
	    for (var loop = 0; loop <commands.length; loop++)
	    {
		    //Get the next command
		    command = commands[loop] ;
		
		    //Get the operation
		    var operation = command["operation"] ;
		    
		    //Get the object type
		    var objecttype = command["objecttype"] ;
		
		    //Get the name
		    var name = command["name"] ;
		    
		    //Get the parameters
		    var params = command["params"] ;
		
		    //Build the id
		    var id = name + "-id" ;
		
		    //Get the element
		    var element = Ext.get(id) ;

			if (params == null)
			    script = name + "." + operation + "();" ;
					
		    switch(objecttype)
		    {
                case "field":
                    //Process all field controls
                    Richa.ExtJS.Field.processCommand(id,element,operation,params,script) ;
                
                    break ;
                    
                case "fieldset":
            	    //Process all fieldset commands
                    Richa.ExtJS.FieldSet.processCommand(id, name,operation,params,script);
                
                    break ;
                    
                case "form":
            	    //Process all Form commands
                    Richa.ExtJS.Form.processCommand(operation,params);
                	
                	break ;
                	
                case "tabpanel":
                	//Process all TabPanel commands
                    Richa.ExtJS.TabPanel.processCommand(name, operation,params);
                	
                	break ;   
                
                case "layout":
                	//Process all Layout commands
                    Richa.ExtJS.Layout.processCommand(name,operation,params);
                	
                	break ; 
                	       
            }
	    }
    }
}	

Richa.ExtJS.Field = 
{
	processCommand : function(id,element,operation,params,script)
    {	
	    switch(operation)
	    {
			case "hide":
				Richa.ExtJS.Field.hide(id,element) ;
				break ;
			case "show":
				Richa.ExtJS.Field.show(id,element) ;	
				break ;
			case "enable":
				Richa.ExtJS.Field.enable(script) ;	
				break ;
			case "disable":
				Richa.ExtJS.Field.disable(script) ;	
				break ;
			case "focus":
				eval(script) ;
				break ;
			case "set":
				Richa.ExtJS.Field.set(id,element,params) ;
		}
	},
	
	set : function(id,element,params)
	{
		element.dom.value = params[0] ;
	},
	
	hide : function(id,element)
	{
		var domNode = Richa.ExtJS.Field.get(id,element) ;
		if (domNode != null)
			domNode.style.display = "none" ;
	},

	show : function(id,element)
	{
		var domNode = Richa.ExtJS.Field.get(id,element) ;
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
		switch(operation)
		{
			case "enable":
				Richa.ExtJS.FieldSet.enable(id,name,script) ;	
				break ;
			case "disable":
				Richa.ExtJS.FieldSet.disable(id,name,script) ;	
				break ;
			case "hide":
				eval(script) ;
			case "show":
				eval(script) ;
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
	            		Richa.ExtJS.Field.enable(tempscript) ;
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
	            		Richa.ExtJS.Field.disable(tempscript) ;
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

Richa.ExtJS.Layout = 
{
	processCommand : function(name, operation,params)
    {	
	    switch(operation)
	    {
	    	case "expand":
				Richa.ExtJS.Layout.expand(name,params) ;
	    		break ;
	    		
			case "collapse":
				Richa.ExtJS.Layout.collapse(name, params) ;
				break ;
				
			case "hide":
				Richa.ExtJS.Layout.hide(name,params) ;
	    		break ;
	    		
			case "show":
				Richa.ExtJS.Layout.show(name, params) ;
				break ;
		}
	},
	
	collapse : function(name, params)
	{
		var region = params[0] ;
		
		var layoutRegion = eval(name + ".getRegion('" + region + "');") ;
		if (layoutRegion != null)
			layoutRegion.collapse() ;
		
	},
	
	expand : function(name, params)
	{
		var region = params[0] ;
		
		var layoutRegion = eval(name + ".getRegion('" + region + "');") ;
		if (layoutRegion != null)
			layoutRegion.expand() ;
		
	},
	
	hide : function(name, params)
	{
		var region = params[0] ;
		
		var layoutRegion = eval(name + ".getRegion('" + region + "');") ;
		if (layoutRegion != null)
			layoutRegion.hide() ;
		
	},
	
	show : function(name, params)
	{
		var region = params[0] ;
		
		var layoutRegion = eval(name + ".getRegion('" + region + "');") ;
		if (layoutRegion != null)
			layoutRegion.show() ;
		
	}
}

Richa.ExtJS.TabPanel = 
{
	processCommand : function(name,operation,params)
    {	
	    switch(operation)
	    {
			case "hide":
				Richa.ExtJS.TabPanel.hideTab(name,params) ;
				break ;
			case "show":
				Richa.ExtJS.TabPanel.showTab(name,params) ;
				break ;
			case "enable":
				Richa.ExtJS.TabPanel.enableTab(name,params) ;
				break ;
			case "disable":
				Richa.ExtJS.TabPanel.disableTab(name,params) ;
				break ;
			case "activate":
				Richa.ExtJS.TabPanel.activate(name,params) ;
				break ;
		}
	},
	
	hideTab : function(name,params)
	{
		var tabpanelitem = params[0] ;
		var script = name + '.hideTab("tabitem-' + tabpanelitem + '-id");' ;
		
		eval(script) ;
	},
	
	showTab : function(name,params)
	{
		var tabpanelitem = params[0] ;
		var script = name + '.unhideTab("tabitem-' + tabpanelitem + '-id");' ;
		
		eval(script) ;
	},
	
	enableTab : function(name,params)
	{
		var tabpanelitem = params[0] ;
		var script = name + '.enableTab("tabitem-' + tabpanelitem + '-id");' ;
		
		eval(script) ;
	},
	
	disableTab : function(name,params)
	{
		var tabpanelitem = params[0] ;
		var script = name + '.disableTab("tabitem-' + tabpanelitem + '-id");' ;
		
		eval(script) ;
	},
	
	activate : function(name,params)
	{
		var tabpanelitem = params[0] ;
		var script = name + '.activate("tabitem-' + tabpanelitem + '-id");' ;
		
		eval(script) ;
	},
	
	
	
}