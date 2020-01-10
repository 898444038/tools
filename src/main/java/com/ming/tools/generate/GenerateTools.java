package com.ming.tools.generate;

import com.ming.tools.thread.ThreadTools;

/**
 * Created by Administrator on 2020/1/9 0009.
 */
public class GenerateTools {
    private volatile static GenerateTools instance = null;

    private GenerateTools(){}

    public static GenerateTools getInstance(){
        if (instance==null){
            synchronized (GenerateTools.class){
                if (instance==null){
                    instance = new GenerateTools();
                }
            }
        }
        return instance;
    }
}
