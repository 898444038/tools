package com.ming.tools.string;

/**
 * Created by Administrator on 2020/1/9 0009.
 */
public class StringUtils {

    public static boolean isEmpty(final CharSequence cs){
        return cs == null || cs.length() == 0;
    }
    public static boolean isNotEmpty(final CharSequence cs){
        return !isEmpty(cs);
    }

}
