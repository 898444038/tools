package com.ming.tools.binding.bean;

/**
 * Created by Administrator on 2020/3/18 0018.
 */
public class ChangeInfo {

    private Boolean change;

    private String name;

    public ChangeInfo() {
    }

    public ChangeInfo(Boolean change, String name) {
        this.change = change;
        this.name = name;
    }

    public Boolean getChange() {
        return change;
    }

    public void setChange(Boolean change) {
        this.change = change;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChangeInfo that = (ChangeInfo) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
