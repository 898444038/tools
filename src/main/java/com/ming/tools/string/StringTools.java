package com.ming.tools.string;

import com.ming.tools.MingTools;

/**
 * Created by Administrator on 2020/1/9 0009.
 */
public class StringTools {
    private volatile static StringTools instance = null;

    private StringTools(){}

    public static StringTools getInstance(){
        if (instance==null){
            synchronized (StringTools.class){
                if (instance==null){
                    instance = new StringTools();
                }
            }
        }
        return instance;
    }

    public boolean isEmpty(String str){
        return StringUtils.isEmpty(str);
    }

    public boolean isNotEmpty(String str){
        return isEmpty(str);
    }
}
