package org.richa.tags.extjs.data ;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.json.JSONArray;
import org.json.JSONException;
import org.richa.event.EventListeners;
import org.richa.tags.extjs.BaseExtJSTag;
import org.richa.util.StoreUtils;
import org.xml.sax.SAXException;

/**
 * This class represents a SimpleStore tag. 
 * This tag is used to define a store that can be bound to controls like ComboBox and Grid
 * This store serialized the data inline 
 * @author ram
 *
 */
public class SimpleStore extends BaseExtJSTag
{
	/**
	 * Generate html and js before the body is processed
	 */
	protected void beforeBody(final XMLOutput output) throws JellyTagException, SAXException
	{
		//Get the name
		String name = getName() ;
		if (isEmpty(name))
			throw new JellyTagException("name is a required parameter for this tag") ;
		
		//Get the bean name
		String bean = (String)getAttribute(BEAN) ;
		if (isEmpty(bean))
			throw new JellyTagException("bean is a required parameter for this tag") ;
		
		//Get the current listener
		String listener = getCurrentListener() ;
		if (isEmpty(listener))
			throw new JellyTagException("A listener tag needs to wrap this tag") ;
		
		//Get the data store handler name
		String dataStore = (String)getAttribute(DATASTORE) ;
		if (isEmpty(dataStore))
			throw new JellyTagException("datastore is a required parameter for this tag") ;
		
		//Create a store
		createStore(name,bean,listener,dataStore) ;
	}
			
	/**
	 * Create a record structure for the bean
	 */
	private void createStore(String name, String bean, String listener, String dataStore) 
	{
		scriptBuffer.appendln("    var " + name + " =  new Ext.data.SimpleStore({") ;
		scriptBuffer.append("        fields:[") ;
		
		//create a new instance of the bean
		try
		{
			Object beaninstance = Class.forName(bean).newInstance() ;
			String fields = StoreUtils.getFieldsAsArray(beaninstance) ;
			
			scriptBuffer.append(fields) ;
		}
		catch (ClassNotFoundException e)
		{ 
			log.error(this, e) ;
		}
		catch (InstantiationException e)
		{
			log.error(this, e) ;
		}
		catch (IllegalAccessException e)
		{
			log.error(this, e) ;
		}
		
		scriptBuffer.appendln("]") ;

		
		try
		{
			List results = executeDataStoreHandler(listener, dataStore) ;
			
			JSONArray rows = StoreUtils.serializeListAsArray(results) ;
			if (rows != null)
				scriptBuffer.appendln("        ,data:" + rows) ;
			
		}
		catch (JSONException e)
		{
			log.error(this, e) ;
		}
		catch (InstantiationException e)
		{
			log.error(this, e) ;
		}
		catch (IllegalArgumentException e)
		{
			log.error(this, e) ;
		}

		catch (InvocationTargetException e)
		{
			log.error(this, e) ;
		}
		catch (IllegalAccessException e)
		{
			log.error(this, e) ;
		}
		
		scriptBuffer.appendln("})") ;
	}
	
	private List executeDataStoreHandler(String eventListener, String dataStore) throws InstantiationException,InvocationTargetException, IllegalAccessException, IllegalArgumentException
	{
		List results = null ;
		
		//Load all the event listeners and handlers in the application
		EventListeners listeners = EventListeners.getInstance() ;
		
		//Strip out the eventListner part from the full path
		int i = eventListener.lastIndexOf('/') ;
		if (i != -1)
			eventListener = eventListener.substring(i + 1) ;
		else
			throw new IllegalArgumentException("Illegal event listener name") ;
		
		//Get an instance of the listener object
		Object listener = listeners.getEventListener(eventListener) ;
		
		//Get the method object for the handler
		Method eventMethod = listeners.getDataStoreHandlerMethod(eventListener, dataStore) ;
		if (eventMethod != null)
		{
			Map<String,String> params = new HashMap<String,String>() ;
			params.put("query", "") ;
			
			Object[] methodParams = new Object[1] ;
			methodParams[0] = params  ;
			
 			results = (List) eventMethod.invoke(listener,methodParams);
		}
		
		return results ;
	}
}

