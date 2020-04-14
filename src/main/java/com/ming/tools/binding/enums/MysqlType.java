package com.ming.tools.binding.enums;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2020/4/1 0001.
 */
public enum MysqlType {

    AUTO_TYPE(Object.class,0,0,null),
    BIT(Boolean.class,1,0,null),
    TINYINT(Byte.class,1,0,null),
    BIGINT(Long.class,20,0,null),
    FLOAT(BigDecimal.class,11,2,null),
    DOUBLE(BigDecimal.class,11,2,null),
    DECIMAL(BigDecimal.class,11,2,null),
    //DATE(Date.class,0,0,null),
    //TIME(Date.class,0,0,null),
    DATETIME(Date.class,0,0,null),
    TIMESTAMP(Date.class,0,0,null),
    VARCHAR(String.class,32,0,null),
    TEXT(String.class,0,0,null),
    ;

    private Class clazz;
    private int length;
    private int point;
    private Object defaultValue;

    MysqlType(){}

    MysqlType(Class clazz){
        this.clazz = clazz;
    }

    MysqlType(Class clazz,int length,int point,Object defaultValue){
        this.clazz = clazz;
        this.length = length;
        this.point = point;
        this.defaultValue = defaultValue;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
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

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public static Class getClazz(MysqlType type) {
        for (MysqlType c : MysqlType.values()) {
            if (type == c) {
                return c.clazz;
            }
        }
        return null;
    }

    public static MysqlType getMysqlType(Class clazz) {
        for (MysqlType type : MysqlType.values()) {
            if (clazz == type.clazz) {
                return type;
            }
        }
        return null;
    }
}
