package org.richa.scanner;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.annotation.MemberValue;

public abstract class Scanner
{
    private static final Logger log = Logger.getLogger(Scanner.class.getName());

    protected  String resourceName;
    protected  ClassLoader classLoader;
   
   public Scanner(String resourceName)
   {
      this( resourceName, Thread.currentThread().getContextClassLoader() );
   }
   
   public Scanner(String resourceName, ClassLoader classLoader)
   {
      this.resourceName = resourceName;
      this.classLoader = classLoader;
      ClassFile.class.getPackage(); //to force loading of javassist, throwing an exception if it is missing
   }
   
   public static String filenameToClassname(String filename)
   {
      return filename.substring( 0, filename.lastIndexOf(".class") )
            .replace('/', '.').replace('\\', '.');
   }
   

    protected void scan() 
    {
      Enumeration<URL> urls;
      try
      {
         urls = classLoader.getResources(resourceName);
      }
      catch (IOException ioe) 
      {
         log.log(Level.WARNING, "could not read: " + resourceName, ioe);
         return;
      }
      
      while ( urls.hasMoreElements() )
      {
         try
         {
            String urlPath = urls.nextElement().getFile();
            urlPath = URLDecoder.decode(urlPath, "UTF-8");
            if ( urlPath.startsWith("file:") )
            {
               // On windows urlpath looks like file:/C: on Linux file:/home
               // substring(5) works for both
               urlPath = urlPath.substring(5);
            }
            if ( urlPath.indexOf('!')>0 )
            {
               urlPath = urlPath.substring(0, urlPath.indexOf('!'));
            }
            else
            {
               File dirOrArchive = new File(urlPath);
               if ( resourceName.lastIndexOf('/')>0 )
               {
                  //for META-INF/simpli.xml
                  dirOrArchive = dirOrArchive.getParentFile();
               }
               urlPath = dirOrArchive.getParent();
            }
            log.info("scanning: " + urlPath);
            File file = new File(urlPath);
            if ( file.isDirectory() )
            {
               handleDirectory(file, null);
            }
            else
            {
               handleArchive(file);
            }
         }
         catch (IOException ioe) 
         {
        	 log.log(Level.WARNING, "could not read entries", ioe);
         }
      }
   }


   private void handleArchive(File file) throws ZipException, IOException
   {
      log.fine("archive: " + file);
      ZipFile zip = new ZipFile(file);
      Enumeration<? extends ZipEntry> entries = zip.entries();
      while ( entries.hasMoreElements() )
      {
         ZipEntry entry = entries.nextElement();
         String name = entry.getName();
         log.fine("found: " + name);
         handleItem(name);
      }
   }

   private void handleDirectory(File file, String path)
   {
      log.fine("directory: " + file);
      for ( File child: file.listFiles() )
      {
         String newPath = path==null ? 
                  child.getName() : path + '/' + child.getName();
         if ( child.isDirectory() )
         {
            handleDirectory(child, newPath);
         }
         else
         {
            handleItem(newPath);
         }
      }
   }

   abstract void handleItem(String name);

   protected ClassFile getClassFile(String name) throws IOException 
   {
      InputStream stream = classLoader.getResourceAsStream(name);
      DataInputStream dstream = new DataInputStream(stream); 

      try 
      { 
         return new ClassFile(dstream); 
      } 
      finally 
      { 
         dstream.close(); 
         stream.close(); 
      }
   }
   
   protected boolean hasAnnotation(ClassFile cf, Class<? extends Annotation> annotationType)
   { 
      AnnotationsAttribute visible = (AnnotationsAttribute) cf.getAttribute( AnnotationsAttribute.visibleTag ); 
      if ( visible != null ) 
      {
         return visible.getAnnotation( annotationType.getName() ) != null; 
      } 
      return false; 
   }

   protected String getAnnotationValue(ClassFile cf, Class<? extends Annotation> annotationType, String memberName)
   { 
      AnnotationsAttribute visible = (AnnotationsAttribute) cf.getAttribute( AnnotationsAttribute.visibleTag ); 
      if ( visible != null ) 
      {
         javassist.bytecode.annotation.Annotation annotation = visible.getAnnotation( annotationType.getName() );
         if (annotation==null)
         {
            return null;
         }
         else
         {
            MemberValue memberValue = annotation.getMemberValue(memberName);
            return memberValue==null ? null : memberValue.toString().replace("\"", ""); //TODO: toString() here is probably Bad ;-)
         }
      }
      else
      {
         return null;
      }
   }

   public static String componentFilename(String name)
   {
      return name.substring( 0, name.lastIndexOf(".class") ) + ".component.xml";
   }

}
