package com.ming.tools.render;

public class RenderParams {

    public String originalTag;//原始
    public String renderTag;//渲染

    public String field;//属性
    public Object value;//名称

    public RenderParams() {}

    public String getOriginalTag() {
        return originalTag;
    }

    public void setOriginalTag(String originalTag) {
        this.originalTag = originalTag;
    }

    public String getRenderTag() {
        return renderTag;
    }

    public void setRenderTag(String renderTag) {
        this.renderTag = renderTag;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
