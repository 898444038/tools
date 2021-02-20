package com.ming.tools.render;

import java.util.List;
import java.util.Map;

public class RenderResult {

    private String original;//原始
    private String render;//渲染

    private Map<String,Object> originalData;//原始参数
    private List<RenderParams> paramsList;

    public RenderResult() {}

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public Map<String, Object> getOriginalData() {
        return originalData;
    }

    public void setOriginalData(Map<String, Object> originalData) {
        this.originalData = originalData;
    }

    public List<RenderParams> getParamsList() {
        return paramsList;
    }

    public void setParamsList(List<RenderParams> paramsList) {
        this.paramsList = paramsList;
    }
}
