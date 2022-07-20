package com.summer.annotation;

import java.lang.annotation.*;

/**
 * 系统日志
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemLog {
    String value() default "";

}
