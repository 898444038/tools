package com.ming.tools.monitor;

import java.net.URL;

//https://blog.csdn.net/XlxfyzsFdblj/article/details/84857643
public class SystemMonitorSigarUtil {
    public static void initSigar() {
        URL url = SystemMonitorSigarUtil.class.getClassLoader().getResource("sigar");
        if(url == null) {
            throw new RuntimeException("当前项目工程resources文件夹下没有Sigar环境文件");
        }
        String path = System.getProperty("java.library.path");
        String osName = System.getProperty("os.name");
        if("Linux".equals(osName)) {
            // Linux使用的是:分割path
            path += ":" + url.getPath();
        } else if(osName.contains("Windows")) {
            path += ";" + url.getPath();
        }
        System.setProperty("java.library.path", path);
    }

    public static void main(String[] args) {
        SystemMonitorSigarUtil sigarUtil = new SystemMonitorSigarUtil();
        // 输出：file:/home/twilight/IdeaProjects/netty/target/classes/sigar
        System.out.println(sigarUtil.getClass().getClassLoader().getResource("sigar"));
        // 输出：Linux
        System.out.println(System.getProperty("os.name"));
    }
}
