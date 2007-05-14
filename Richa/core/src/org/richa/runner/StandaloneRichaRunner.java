package org.richa.runner;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Use Jelly to execute a Richa Pages
 * @author ram
 */
public class StandaloneRichaRunner extends RichaRunner
{
    protected URL getResource(String pageName) throws MalformedURLException
    {
        URL url = null;
        if (pageName != null)
        {
            String newName = pageName.replaceAll("\\\\", "/");
            url = new URL("file:///" + newName);
        }
        
        return url;
    }
    
    protected boolean isCachingEnabled()
    {
        return false;
    }
}