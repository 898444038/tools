package com.ming.tools.binding.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    //首字母大写
    public static String firstUpperCase(String str){
        char[] chars = str.toCharArray();
        chars[0]-=32;
        return String.valueOf(chars);
    }

    //首字母小写
    public static String firstLowerCase(String str){
        char[] chars = str.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);
    }

    //下划线转驼峰
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    public static String lineToHump(String str){
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()){
            matcher.appendReplacement(sb,matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    //驼峰转下划线
    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    public static String humpToLine(String str){
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()){
            matcher.appendReplacement(sb,"_"+matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    //类名转别名
    public static String getHump(String str){
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()){
            sb.append(matcher.group(0).toLowerCase());
        }
        return sb.toString();
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }
}
