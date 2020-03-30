package com.ming.tools.binding.utils;

/**
 * Created by Administrator on 2020/3/18 0018.
 */
public class StringUtils {

    public static String setName(String name){
        char[] chars = name.toCharArray();
        chars[0] = (char)(chars[0] - 32);
        return "get" + new String(chars);
    }

    public static String getName(String name){
        char[] chars = name.toCharArray();
        chars[0] = (char)(chars[0] - 32);
        return "get" + new String(chars);
    }

    public static String property(String name){
        name = name.replace("set","");
        name = name.replace("get","");
        char[] chars = name.toCharArray();
        chars[0] = (char)(chars[0] + 32);
        return new String(chars);
    }

    //首字母大写
    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}
