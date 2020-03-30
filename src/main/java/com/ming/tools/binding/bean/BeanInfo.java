package com.ming.tools.binding.bean;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2020/3/18 0018.
 */
public class BeanInfo {

    private Map<String,Boolean> changeMap;

    private Object obj;

    public BeanInfo() {}

    public Map<String, Boolean> getChangeMap() {
        return changeMap;
    }

    public void setChangeMap(Map<String, Boolean> changeMap) {
        this.changeMap = changeMap;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
