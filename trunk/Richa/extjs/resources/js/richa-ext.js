/* Richa EXTJS support code */

/** Build the data to be posted for the Form **/
RichaBuildFormData = function(form)
{
	var i,j ;
	var submit ="";
	var multivalue = "";
    var fieldName = ""

	for (var i=0; i < form.elements.length; i++) 
	{	 
		value = '' ;	
		multivalue = '' ;				
		
		var field = form.elements[i]; 
		if (field.disabled)
			continue ;
		
		//Field Name
		fieldName = field.name ;
		
		var tagType = field.nodeName.toUpperCase() ;			
		if (tagType != "INPUT" && tagType != "TEXTAREA" && tagType != "SELECT" && tagType != "OPTION") 
			continue ;
		
		if (field.type=='select-multiple') 
		{ 						
			multivalue = "[" ;
			for (j=0;j < field.options.length; j++)
		 	{
		 		if (field.options[j].selected)
		 		{
		 			if (multivalue != "")
		 				multivalue = multivalue + "," ;
		 				
		 	    	multivalue += '"' + encodeURIComponent(field.options[j].value) + '"';
		 	   	}
		 	}
		 			
		    multivalue += "]" ;
		    
		    if (submit != "")
		        submit += "," ;
		        
			submit += '"' + fieldName + '":"' + multivalue + '"\n' ; 
		}
		else if (field.type=='select-one') 
		{ 
			if (field.selectedIndex >= 0)
				value = field.options[field.selectedIndex].value; 
		} 
		else if (field.type == "radio")
		{ 						
			if (field.checked) 
				value = field.value; 
			else
				continue ;
		}
		else if (field.type == "checkbox")
		{ 						
			if (field.checked) 
				value = field.value; 
			else
				continue ;
		} 
		else
		{ 			
			value = field.value; 
		}
		
		if (value != '') 
		{ 
			value = encodeURIComponent(value); 
		}
		
		if(multivalue == "")
		{
			if (submit != "")
			    submit += "," ;
			    
			submit += '"' + fieldName + '":"' + value + '"\n' ; 
		} 

		value = '' ;	
		multivalue = '' ;				
	} 		
	
	return (submit) ;				
}

/** Get the form name for a field **/
RichaFindForm = function(field)
{
	var form = field ;
	while (true)
	{
		tagType = form.nodeName.toUpperCase();
		if (tagType == "FORM")
			break ;
		
		form = form.parentNode ;
	}
	
	return form ;
}

/** Dispatch event to the server **/
RichaDispatchEvent = function(event)
{	
	//Get the element
	var element = this.dom ;

	//Get the data from the parameters	
   	var listener = element.getAttribute("listener") ; 
   	var handler = element.getAttribute("handler") ; 
   	var senddata = element.getAttribute("senddata") ; 
	
	//Build the url
	var url = listener + "/" + handler ;   
	
	//Strip out the listener name
	var i = listener.lastIndexOf("/") ;
	if (i != -1)
		listener = listener.substring(i + 1) ;
	
	//Build the before method name
   	var methodName = "before_" + listener + "_" + handler ;
   	var eventData ;
   	var params ;
   	var formData ;
   	
   	//Check if there is a function that needs to be fired for this handler
   	if (eval("typeof " + methodName + " == 'function'"))
   	{
	    eventData = eval("" + methodName +"()") ;	
	    
	   	//Check if the event needs to be cancelled
   		if (eventData.cancelEvent)
   	    	return ;
   	    
   	    //Get the data to send
   	    params = eventData.sendData ;
   	}

	//Build Params
	if (params == null)
	{
		if (senddata == "field")
		    params = '"' + element.name + '":"'  + encodeURIComponent(element.value) + '"\n' ;
		else if (senddata == "form")
		{
			//Find the form
			var form = RichaFindForm(element) ;
			params = RichaBuildFormData(form) ;
		}
		else if (senddata == "page")
		{
			params = "" ;
			var forms = document.body.getElementsByTagName("FORM") ;
			for (var i = 0; i < forms.length; i++) 
			{		
				formData = RichaBuildFormData(forms[i]) ;
				if (params != "")
					params  = params + "," ;
				
				params = params + formData ;
			}
		}
	}
	  	
	//Add other event parameters
	if (params != "")
		params = params + ',"pagex:":"'
	else
		params = params + '"pagex:":"'
		
	params = params + event.getPageX() + '"\n' ;
	params = params + ',"pagey":"' + event.getPageY() + '"\n' ;
	params = params + ',"ctrlKey":"' + event.ctrlKey + '"\n' ;
	params = params + ',"shiftKey":"' + event.shiftKey + '"\n' ;
	params = params + ',"altKey":"' + event.altKey + '"\n' ;
	
	params = '{\n' + params + '}\n' ;
	
	//Dispatch the event
	Ext.lib.Ajax.request('POST', url, {success: RichaSuccess, failure: RichaFailure}, params);
}

/** Event Data object **/
RichaEventData = function()
{
	var sendData ;
	var cancelEvent ;
}

/** Function called whtn the request is successful **/
RichaSuccess = function(response)
{
	var res = Ext.util.JSON.decode(response.responseText) ;
	
	if (res.CODE == "FAIL") 
		RichaShowErrorDialog("Server Error",res.DATA) ;
	else
	{
		var operations = Ext.util.JSON.decode(res.DATA);
		RichaProcessResponse(operations) ;
	}
}

/** Function called when a client request fails **/
RichaFailure = function(response)
{
	RichaShowErrorDialog("Server Error",response.responseText) ;
}
/** Show an error Dialog **/
RichaShowErrorDialog = function(title, text)
{
	//Create an element
	var dlgElem = document.getElementById("serverErrorDlg") ;
	if (dlgElem == null)
	{
		dlgElem = document.createElement("div") ;
		document.body.appendChild(dlgElem) ;
		
		//Set the id
		dlgElem.setAttribute("id","serverErrorDlg") ;
		
		dlgElem.style.fontFamily = "'Lucida Console', 'Bitstream Vera Sans Mono', 'Courier New', Monaco, Courier, monospace;" 
		dlgElem.style.whiteSpace = "pre;" 
		dlgElem.style.lineHeight = "1.4em;" 
		dlgElem.style.fontSize =  "70%;" 
	}
	
	var dlg = new Ext.BasicDialog(dlgElem, {
        height: 500,
        width: 600,
        minHeight: 100,
        minWidth: 150,
        modal: true,
        collapasable: false,
        title: title,
        autoScroll: true
    });
    dlg.addKeyListener(27, dlg.hide, dlg); // ESC can also close the dialog
    dlg.addButton('OK', dlg.hide, dlg);    // Could call a save function instead of hiding
    
    dlg.body.update(text) ;
    
    dlg.show();
}

/** Function used to bind an event to an element **/
RichaBindEvent = function(control,controlType, event, jshandler, listener, handler, sendData)
{
	//Get the controls element
	var el = control.getEl() ;

	//If it is a button, then ExtJS send a handle to the table, find the button in the table
	if (controlType == "Ext.Button")
		el = el.child("button:first") ;
	
	//Get the DOM element
	domEl = el.dom ;
	
	//Set the attributes
	domEl.setAttribute("listener",listener) ;
	domEl.setAttribute("handler",handler) ;
	domEl.setAttribute("senddata", sendData) ;
	
	//Bind the event
	el.on(event,jshandler) ;
}

RichaProcessResponse = function(commands)
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
		
		//Build the id
		var id = name + "-id" ;
		
		//Get the element
		var element = Ext.get(id) ;

		//Get the tag name		
		var tagName = element.dom.tagName.toLowerCase() ;

		var script = name + "." + operation + "();" ;
		
		switch(tagName)
		{
            case "input":
            	//Get the type of the input control
                var type = element.dom.getAttribute("type") ;
                
                //Process all input controls
                RichaProcessInputCommand(id,element,operation,script) ;
                
                break ;
            case "fieldset":
            	//Process all fieldset controls
                RichaProcessFSCommand(id, name,operation,script);
                
                break ;
        }
	}
}

RichaProcessInputCommand = function(id,element,operation,script)
{	
	switch(operation)
	{
		case "hide":
			RichaHideFormElement(id,element) ;
			break ;
		case "show":
			RichaShowFormElement(id,element) ;	
			break ;
		case "enable":
			RichaEnableFormElement(script) ;	
			break ;
		case "disable":
			RichaDisableFormElement(script) ;	
			break ;
		case "focus":
			eval(script) ;
			break ;
	}
}

RichaProcessFSCommand = function(id,name,operation,script)
{	
	eval(script) ;
	
	switch(operation)
	{
		case "enable":
			RichaEnableFS(id,name,script) ;	
			break ;
		case "disable":
			RichaDisableFS(id,name,script) ;	
			break ;
	}
}

RichaEnableFS = function(id,name,script)
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
            		RichaEnableFormElement(tempscript) ;
            	}
            }
        }
    }
}

RichaDisableFS = function(id,name,script)
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
            		RichaDisableFormElement(tempscript) ;
            	}
            }
        }
    }
}
RichaHideFormElement = function(id,element)
{
	var domNode = RichaGetFormDivElement(id,element) ;
	if (domNode != null)
		domNode.style.display = "none" ;
}

RichaShowFormElement = function(id,element)
{
	var domNode = RichaGetFormDivElement(id,element) ;
	if (domNode != null)
		domNode.style.display = "" ;
}

RichaDisableFormElement = function(script)
{
	eval(script) ;
}

RichaEnableFormElement = function(script)
{
	eval(script) ;
}

RichaGetFormDivElement = function(id,element)
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