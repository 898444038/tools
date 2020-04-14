package com.ming.tools.binding.bean;

/**
 * Created by Administrator on 2020/4/2 0002.
 */
public class MappingInfo {

    private Integer classHashCode;

    private BeanTableInfo beanTableInfo;

    public MappingInfo() {
    }

    public Integer getClassHashCode() {
        return classHashCode;
    }

    public void setClassHashCode(Integer classHashCode) {
        this.classHashCode = classHashCode;
    }

    public BeanTableInfo getBeanTableInfo() {
        return beanTableInfo;
    }

    public void setBeanTableInfo(BeanTableInfo beanTableInfo) {
        this.beanTableInfo = beanTableInfo;
    }
}
