/* Richa EXTJS support code */

function handleEvent(listener, handler, data)
{
	ajaxFunction(listener + "/" + handler, Ext.util.JSON.encode(data));
}

function ajaxFunction(event, data)
{
	var xmlHttp;
	try
	{
		// Firefox, Opera 8.0+, Safari
		xmlHttp=new XMLHttpRequest();
	}
	catch (e)
	{
		// Internet Explorer
		try
		{
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (e)
		{
			try
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (e)
			{
				alert("Your browser does not support AJAX!");
				return false;
			}
		}
	}

	xmlHttp.onreadystatechange=function()
	{
		if(xmlHttp.readyState==4)
		{
			// if "OK"
			if (xmlHttp.status==200)
			{		
				eval(xmlHttp.responseText);
			}
			//There was a problem
			else
			{
				try
				{
					showErrorDialog('There was an error while trying to handle your request.  Please try your request again, or contact the system administrator.', xmlHttp.responseText);
				}
				catch(e)
				{
					alert('There was an error while trying to handle your request.  Please try your request again, or contact the system administrator.');
				}
			}
		}
	}

	xmlHttp.open("POST","event/"+event, true);
	if(data != null)
	{
		xmlHttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xmlHttp.send("data=" + data);
	}
	else
	{
		xmlHttp.send(null);
	}
} 

function showErrorDialog(summary, detail)
{
        dialog = new Ext.LayoutDialog("error-dlg", { 
                modal:true,
                width:600,
                height:400,
                shadow:true,
                minWidth:300,
                minHeight:300,
                proxyDrag: true,
                title: 'Error',
             center: {
                 autoScroll:true,
                 tabPosition: 'top',
                 closeOnTab: true,
                 alwaysShowTabs: true
             }
        });
        dialog.addKeyListener(27, dialog.hide, dialog);
        dialog.addButton('Close', dialog.hide, dialog);
        
        var layout = dialog.getLayout();
        layout.beginUpdate();
		layout.add('center', new Ext.ContentPanel('center', {title: 'Summary'}, summary));
        layout.add('center', new Ext.ContentPanel(Ext.id(), {
                                autoCreate:true, title: 'Detail', background:true}, detail));
		layout.endUpdate();
    dialog.show();
}