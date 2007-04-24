package org.richa.annotations;

public @interface ParamMap
{
	public static final String DEFAULT_EVENT_HANDLER = "!defaultEventHandler!";
	public static final String DEFAULT_METHOD_PARAM = "!defaultMethodParam!";

	String name() default DEFAULT_EVENT_HANDLER;
	String methodParam() default DEFAULT_METHOD_PARAM;
}
