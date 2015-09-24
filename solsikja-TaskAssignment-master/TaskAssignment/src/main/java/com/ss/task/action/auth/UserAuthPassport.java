package com.ss.task.action.auth;

import java.lang.annotation.*;

/**
 * Created by liymm on 2015-01-30.
 */
@Documented
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserAuthPassport {
    boolean validate() default true;
}
