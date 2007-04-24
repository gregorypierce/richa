package org.richa.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value=ElementType.TYPE)
public @interface EventListener
{
	public static final String DEFAULT_LISTENER = "!listnername!";
	String value() default DEFAULT_LISTENER;
}
