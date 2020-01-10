package com.ming.tools;

import com.ming.tools.string.StringUtils;
import com.ming.tools.thread.pool.ThreadPoolUtils;

/**
 * Created by Administrator on 2020/1/9 0009.
 */
public class MingToolsTest {
    public static void main(String[] args) {
        MingTools.thread().threadPool().execute(new Runnable() {
            public void run() {

            }
        },2);

        ThreadPoolUtils.execute(new Runnable() {
            public void run() {

            }
        });
    }

}
