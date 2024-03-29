package org.richa.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value=ElementType.METHOD)
public @interface PageBindHandler
{
	public static final String DEFAULT_BIND = "!defaultbind!";
	
	String value() default DEFAULT_BIND;
}
