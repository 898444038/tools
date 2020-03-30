package com.ming.tools.binding.utils;

/**
 * Created by Administrator on 2020/3/18 0018.
 */
public class LogValue {
    /**
     * 中文名称
     */
    private String name;
    /**
     * 属性值
     */
    private Object value;
    /**
     * 日期格式化
     */
    private String format;

    public LogValue(String name, Object value,String format) {
        super();
        this.name = name;
        this.value = value;
        this.format=format;

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }

}
