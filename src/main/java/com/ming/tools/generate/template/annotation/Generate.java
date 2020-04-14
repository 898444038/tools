package com.ming.tools.generate.template.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2020/3/16 0016.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Generate {

    boolean isEffective() default true;//是否生效

    boolean isCover() default false;//是否覆盖

    String baseUrl() default "";//基础路径

    String desc() default "";//描述

    String tableName() default "";//表名

    String classMapping() default "";//请求类路径

}
