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
		params = params + '",pagex:":"'
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

RichaSuccess = function(response)
{
	alert ("Success") ;
}

RichaFailure = function(response)
{
	var dlg = new Ext.BasicDialog("my-dlg", {
        height: 200,
        width: 300,
        minHeight: 100,
        minWidth: 150,
        modal: true,
        title: 'Server Error'
        autoScroll: true
    });
    dlg.addKeyListener(27, dlg.hide, dlg); // ESC can also close the dialog
    dlg.addButton('OK', dlg.hide, dlg);    // Could call a save function instead of hiding
    dlg.addButton('Cancel', dlg.hide, dlg);
    
    dlg.body.updateText = response.responseText ;
    
    dlg.show();
}

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
