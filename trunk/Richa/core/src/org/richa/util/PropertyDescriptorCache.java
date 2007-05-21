package org.richa.util;

import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.PropertyUtils;


/**
 * This class provides a cache of PropertyDescriptor array
 * @author ram
 *
 */
public class PropertyDescriptorCache
{
	private static LRUCache<String,PropertyDescriptor[]> propCache = new LRUCache<String,PropertyDescriptor[]>(50) ;
	
	/**
	 * Get a property descriptor array for a bean
	 */
	public static PropertyDescriptor[] get(Object bean)
	{		
		PropertyDescriptor[] props = null ;
		
		if (bean != null)
		{
			props =  (PropertyDescriptor[])propCache.get(bean.getClass().getName()) ;
			if (props == null)
			{
				//Get the descriptor
				props = getDescriptor(bean) ;
				
				put (bean.getClass().getName(), props) ;
			}
		}
		
		return props ;
	}
	
	/**
	 * Store the pre-compiled script instance in the cache
	 */
	public static void put(String beanName, PropertyDescriptor[] props)
	{
		if (beanName != null && props != null)
		{	
			//Store it in the cache
			propCache.put(beanName, props) ;
		}
	}
	
	/**
	 * Get the properties for a bean
	 */
	private static PropertyDescriptor[] getDescriptor(Object bean)
	{
		//Get all the properties in the bean
		PropertyDescriptor[] props = null ;		
		props = PropertyUtils.getPropertyDescriptors(bean) ;

		return props ;
	}
}
