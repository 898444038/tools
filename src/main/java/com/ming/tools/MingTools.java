package com.ming.tools;

import com.ming.tools.generate.GenerateTools;
import com.ming.tools.string.StringTools;
import com.ming.tools.thread.ThreadTools;

/**
 * Created by Administrator on 2020/1/9 0009.
 */
public class MingTools {
    //自动生成工具类
    public static GenerateTools generate(){
        return GenerateTools.getInstance();
    }
    //线程工具类
    public static ThreadTools thread(){
        return ThreadTools.getInstance();
    }
    //字符串工具类
    public static StringTools string(){
        return StringTools.getInstance();
    }

    // 字符串相关的工具类
    public static void charsetUtil(){}
    // Class与反射相关的一些工具类
    public static void classUtil(){}
    // 中文相关的工具类
    public static void chinesUtil(){}
    // 转换相关的工具类
    public static void convertUtil(){}
    // 日期时间相关的工具类
    public static void dateUtil(){}
    // Email相关的工具类
    public static void emailUtil(){}
    // 文件相关的工具类
    //public static FileUtils fileUtil(){return FileUtils.builder();}
    // 打印相关的工具类
    //public static PrintUtils printUtil(){return PrintUtils.builder();}
    // 属性文件相关的工具类
    public static void propUtil(){}
    // 随机操作的相关的工具类
    //public static RandomUtils randomUtil(){return RandomUtils.builder();}
    // 正则相关的工具类
    public static void regexUtil(){}
    // 安全相关的工具类
    public static void secUtil(){}
    // 流
    public static void streamUtil(){}
    // system/ip
    public static void systemUtil(){}
    // zip
    public static void zipUtil(){}
    // web
    public static void webUtil(){}

    // 数字
    public static void number(){}
    // 函数
    public static void math(){}
    // json
    public static void json(){}
    // 集合
    public static void collection(){}
    // unicode
    public static void unicode(){}
    // excel
    public static void excel(){}
    // spring
    public static void spring(){}
}
