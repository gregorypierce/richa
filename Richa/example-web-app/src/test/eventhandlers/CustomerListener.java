package test.eventhandlers;

import org.richa.annotations.EventHandler;
import org.richa.annotations.EventListener;

@EventListener("customerListener")
public class CustomerListener
{
	@EventHandler("customerDataHandler")
	public String customerDataHandler(String data)
	{
		return "Ext.MessageBox.alert('CustomerListener', 'The server got the data for the customer: "
				+ data.toString() + "');";
	}
}
