package com.ming.tools.binding.anno.mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2020/3/18 0018.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    /*表名*/
    String name() default "";
    /*别名*/
    String aliasName() default "";
    /*描述*/
    String desc() default "";
    String engine() default "MyISAM";
    String autoIncrement() default "1";
    String charset() default "utf8";
    String rowFormat() default "DYNAMIC";
}
