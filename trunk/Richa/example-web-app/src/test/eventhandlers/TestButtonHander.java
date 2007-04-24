package test.eventhandlers;

import org.richa.annotations.EventHandler;
import org.richa.annotations.EventListener;

@EventListener("testButton")
public class TestButtonHander
{
	@EventHandler()
	public String onclick()
	{
		return "alert(\"The server time is now: " + new java.util.Date().toString() + "\");";
	}

	//@EventHandler(name="onchange", param=[@ParamMap(name="testField", methodParam="eventParam")])
	public String testingChange(String eventParam)
	{
		return "Test";
	}
}
