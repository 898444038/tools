package com.ming.tools.binding.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2020/3/18 0018.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LogCompar {
    /**
     * 汉字全称
     * @return
     */
    String name();

    /**
     * Date 如何格式化，默认可以为空
     * @return
     */
    String dateFormat() default "";

}
