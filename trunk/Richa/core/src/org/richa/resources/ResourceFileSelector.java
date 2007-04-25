package org.richa.resources ;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSelectInfo;
import org.apache.commons.vfs.FileSelector;

public class ResourceFileSelector implements FileSelector
{
	private String resourceName ;
	
	public ResourceFileSelector(String resourceName)
	{
		this.resourceName = resourceName ;
	}
	
	public boolean includeFile(FileSelectInfo fileInfo) throws Exception
    {
        FileObject fo = fileInfo.getFile();
        return fo.getName().getPath().equals(resourceName) ;
    }

    public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception
    {
        return true;
    }                   
}