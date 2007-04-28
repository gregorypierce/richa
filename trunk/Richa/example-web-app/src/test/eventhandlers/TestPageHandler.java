package test.eventhandlers;

import org.richa.annotations.EventHandler;
import org.richa.annotations.EventListener;

@EventListener("pageListener")
public class TestPageHandler
{
	@EventHandler("pageHandler")
	public String testPageHandler()
	{
		return "Ext.MessageBox.alert('PageListener', 'You called the testPageHandler method on the TestPageListener class.');";
	}
}
