package com.ming.tools.binding.bean;

import com.ming.tools.binding.enums.MysqlType;

/**
 * Created by Administrator on 2020/4/1 0001.
 */
public class BeanColumnInfo {
    /*列名*/
    private String name;
    /*列类型*/
    private MysqlType type;
    /*java属性类型*/
    private Class property;
    /*列类型长度*/
    private int length;
    /*列类型小数点*/
    private int point;
    /*默认值*/
    private String defaultValue;
    /*默认可以为空*/
    private boolean isNull;
    /*是否是主键*/
    private boolean isKey;
    /*注释*/
    private String comment;

    public BeanColumnInfo() {
    }

    public Class getProperty() {
        return property;
    }

    public void setProperty(Class property) {
        this.property = property;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MysqlType getType() {
        return type;
    }

    public void setType(MysqlType type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean getIsNull() {
        return isNull;
    }

    public void setIsNull(boolean aNull) {
        isNull = aNull;
    }

    public boolean getIsKey() {
        return isKey;
    }

    public void setIsKey(boolean isKey) {
        this.isKey = isKey;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
