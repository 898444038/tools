package com.ming.tools.binding.anno.mapping;

import com.ming.tools.binding.enums.MysqlType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2020/3/18 0018.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    /*列名*/
    String name() default "";
    /*列类型*/
    MysqlType type() default MysqlType.AUTO_TYPE;
    /*列类型长度*/
    int length() default 0;
    /*列类型小数点*/
    int point() default 0;
    /*默认值*/
    String defaultValue() default "";
    /*默认可以为空*/
    boolean isNull() default true;

}
