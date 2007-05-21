package org.richa.tags.extjs.data ;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.richa.tags.extjs.BaseExtJSTag;
import org.richa.util.StoreUtils;
import org.xml.sax.SAXException;

/**
 * This class represents a RemoteStore tag. 
 * This tag is used to define a store that can be bound to controls like ComboBox and Grid 
 * @author ram
 *
 */
public class RemoteStore extends BaseExtJSTag
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
		
		//Create a record structure
		createRecordStructure(name,bean) ;
		
		//Create a JSON Reader
		createJSONReader(name,bean) ;
		
		//Create a store
		createStore(name,bean,listener,dataStore) ;
	}
			
	/**
	 * Create a record structure for the bean
	 */
	private void createRecordStructure(String name, String bean)
	{
		scriptBuffer.appendln("    var RecordDef_" + name + " =  Ext.data.Record.create([") ;
		
		//create a new instance of the bean
		try
		{
			Object beaninstance = Class.forName(bean).newInstance() ;
			String fields = StoreUtils.getFieldsAsMap(beaninstance) ;
			
			scriptBuffer.appendln(fields) ;
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
		
		scriptBuffer.appendln("    ]);") ;
		
	}
	
	/**
	 * Create a JSON reader for the bean
	 */
	private void createJSONReader(String name, String bean)
	{
		scriptBuffer.appendln("    var JSONReader_" + name + " =  new Ext.data.JsonReader({") ;
		scriptBuffer.appendln("        totalProperty: \"results\", ") ;
	    scriptBuffer.appendln("        root: \"rows\", ") ;
		scriptBuffer.appendln("    }, RecordDef_" + name + ");") ;
	}
	
	/**
	 * Create a Data Store for the bean 
	 */
	private void createStore(String name, String bean, String listener, String handler)
	{
		String temp = listener.replaceAll("/event/", "/datastore/") ;
		String url =  temp + "/" + handler;
		
		scriptBuffer.appendln("    var " + name + " =  new Ext.data.Store({") ;
		scriptBuffer.appendln("        proxy: new Ext.data.HttpProxy({url:'" + url + "'}),") ;
		scriptBuffer.appendln("        reader: JSONReader_" + name ) ;
		scriptBuffer.appendln("    });") ;
	}
}

