package com.ming.tools.binding.core;

import com.ming.tools.binding.config.Config;
import com.ming.tools.binding.utils.PropsUtil;

import java.util.Properties;

/**
 * Created by jack on 2017/5/22.
 * 属性文件助手类
 */
public class ConfigHelper {
    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(Config.CONFIG_FILE);



    /**
     * 获取JDBC驱动
     */
    public static String getJdbcDriver(){
        return PropsUtil.getString(CONFIG_PROPS, Config.JDBC_DRIVER);
    }
    /**
     * 获取JDBC URL
     */
    public static String getJdbcUrl(){
        return PropsUtil.getString(CONFIG_PROPS,Config.JDBC_URL);
    }
    /**
     * 获取JDBC 用户名
     */
    public static String getJdbcUsername(){
        return PropsUtil.getString(CONFIG_PROPS,Config.JDBC_USERNAME);
    }
    /**
     * 获取JDBC 密码
     */
    public static String getJdbcPassword(){
        return PropsUtil.getString(CONFIG_PROPS,Config.JDBC_PASSWORD);
    }
    /**
     * 获取应用基础包名
     */
    public static String getAppBasePackage(){
        return PropsUtil.getString(CONFIG_PROPS,Config.APP_BASE_PACKAGE);
    }
    /**
     * 获取应用jsp路径
     */
    public static String getAppJspPath(){
        return PropsUtil.getString(CONFIG_PROPS,Config.APP_JSP_PATH,"/WEB-INF/views/");
    }
    /**
     * 获取应用静态资源路径
     */
    public static String getAppAssetPath(){
        return PropsUtil.getString(CONFIG_PROPS,Config.APP_ASSET_PATH,"/assets/");
    }
}