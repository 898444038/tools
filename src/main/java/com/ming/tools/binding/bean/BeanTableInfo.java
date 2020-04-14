package com.ming.tools.binding.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/4/1 0001.
 */
public class BeanTableInfo {

    private String tableName;

    private String aliasName;

    private String desc;

    private String engine;
    private String autoIncrement;
    private String charset;
    private String rowFormat;

    private List<BeanColumnInfo> columnInfoList;


    public BeanTableInfo() {
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(String autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getRowFormat() {
        return rowFormat;
    }

    public void setRowFormat(String rowFormat) {
        this.rowFormat = rowFormat;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<BeanColumnInfo> getColumnInfoList() {
        return columnInfoList;
    }

    public void setColumnInfoList(List<BeanColumnInfo> columnInfoList) {
        this.columnInfoList = columnInfoList;
    }
}
