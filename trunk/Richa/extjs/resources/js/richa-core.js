/***************************************
* Core Richa Objects
* Author: Ram
*/

Richa = {}

Richa.Core = 
{
	/** 
	* Event Data object 
	**/
	eventData : function()
	{
		var sendData ;
		var cancelEvent ;
	},
	
	
	/** 
	* Bind an event to an element 
	**/
	bindEvent : function(control,controlType, event, jshandler, listener, handler, sendData) 
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
	},
	
	
	/**
	* Send an event to the server with the appropriate data
	**/
	dispatchEvent : function(event)
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
				var form = Richa.Core.findForm(element) ;
				params = Richa.Core.buildFormData(form) ;
			}
			else if (senddata == "page")
			{
				params = "" ;
				var forms = document.body.getElementsByTagName("FORM") ;
				for (var i = 0; i < forms.length; i++) 
				{		
					formData = Richa.Core.buildFormData(forms[i]) ;
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
		Ext.lib.Ajax.request('POST', url, {success: Richa.Core.success, failure: Richa.Core.failure}, params);
	},
	
	/** 
	* Build the data to be posted for the Form 
	**/
	buildFormData : function(form)
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
	},

	/** 
	* Get the form name for a field 
	**/
	findForm : function(field)
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
	},
	
	/** 
	* Function called whtn the request is successful 
	**/
	success : function(response)
    {
	    var res = Ext.util.JSON.decode(response.responseText) ;
	
	    if (res.CODE == "FAIL") 
		    Richa.Core.showErrorDialog("Server Error",res.DATA) ;
	    else
	    {
		    var operations = Ext.util.JSON.decode(res.DATA);
		    Richa.ExtJS.Core.processResponse(operations) ;
	    }
    },

    /** 
    * Function called when a client request fails 
    **/
    failure : function(response)
    {
	    Richa.Core.showErrorDialog("Server Error",response.responseText) ;
    },

    /** 
    * Show an error Dialog 
    **/
    showErrorDialog : function(title, text)
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
}
