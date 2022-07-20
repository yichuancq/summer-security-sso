package com.summer.annotation;

import java.lang.annotation.*;

/**
 * 系统日志
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented //生成文档
public @interface SystemLog {
    String value() default "";

}
