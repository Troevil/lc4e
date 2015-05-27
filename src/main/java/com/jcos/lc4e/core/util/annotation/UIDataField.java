package com.jcos.lc4e.core.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by teddy on 2015/5/21.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UIDataField {

    public  boolean useCache() default true; //if use Vars  ,the useCache will be aborted

    public String functionName() default "";

    public String attributeName() default "";

    public int[] useVarIndex() default {};

    public String cacheName() default "data";

    public String key() default "";

}
