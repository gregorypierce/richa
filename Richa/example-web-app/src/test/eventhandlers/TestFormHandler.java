package test.eventhandlers;

import org.richa.annotations.EventHandler;
import org.richa.annotations.EventListener;

@EventListener("formListener")
public class TestFormHandler
{
	@EventHandler("formHandler")
	public String testFormHandler()
	{
		return "Ext.MessageBox.alert('FormListener', 'You called the testFormHandler method on the TestFormListener class.');";
	}
}
