package org.richa.scanner;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javassist.bytecode.ClassFile;

import org.richa.annotations.EventHandler;
import org.richa.annotations.EventListener;
import org.richa.event.EventListenerMetaData;

public class EventListenerScanner extends Scanner
{
	private static final Logger log = Logger.getLogger(EventListenerScanner.class.getName());

	private Map<String, EventListenerMetaData> classes;

	public EventListenerScanner(String resourceName)
	{
		super(resourceName);
	}

	public EventListenerScanner(String resourceName, ClassLoader classLoader)
	{
		super(resourceName, classLoader);
	}

	/**
	 * Returns only Richa Event Handlers (ie: classes annotated with
	 * 
	 * @EventHandler)
	 */
	public Map<String, EventListenerMetaData> getClasses()
	{
		if (classes == null)
		{
			classes = new HashMap<String, EventListenerMetaData>();
			scan();
		}
		return classes;
	}

	@Override
	protected void handleItem(String name)
	{
		if (name.endsWith(".class"))
		{
			String classname = filenameToClassname(name);
			try
			{
				ClassFile classFile = getClassFile(name);
				boolean installable = hasAnnotation(classFile,
						EventListener.class);
				if (installable)
				{
					String eventListenerName = getEventName(classFile);
					if (log.isLoggable(Level.FINE))
						log.fine("found event handler class: " + name
								+ " for event listener: " + eventListenerName);
					// Find all annotated methods
					Class clazz = classLoader.loadClass(classname);
					EventListenerMetaData elmd = new EventListenerMetaData(clazz);
					
					for(Method method : clazz.getMethods())
					{
						EventHandler eh = method.getAnnotation(EventHandler.class);
						if(eh != null)
						{
							String eventHandlerName = eh.value();
							if(eventHandlerName.equals(EventHandler.DEFAULT_EVENT))
							{
								elmd.addEventHandler(method.getName(), method);
							}
							else
							{
								elmd.addEventHandler(eventHandlerName, method);
							}
						}
					}
					
					classes.put(eventListenerName, elmd );
				}
			}
			catch (ClassNotFoundException cnfe)
			{
				log.log(Level.FINE, "could not load class: " + classname, cnfe);

			}
			catch (NoClassDefFoundError ncdfe)
			{
				log.log(Level.FINE,
						"could not load class (missing dependency): "
								+ classname, ncdfe);

			}
			catch (IOException ioe)
			{
				log.log(Level.FINE, "could not load classfile: " + classname,
						ioe);
			}
		}
	}

	private String getEventName(ClassFile classFile)
	{
		String name = getAnnotationValue(classFile, EventListener.class,
				"value");
		if (name == null)
		{
			name = classFile.getName();
		}
		return name;
	}
}
