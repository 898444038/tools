package com.ming.tools.image;

/**
 * Created by Administrator on 2020/3/30 0030.
 */
public class ImageContent {

    private Integer start;
    private Integer end;
    private String content;

    public ImageContent() {
    }

    public ImageContent(Integer start, Integer end, String content) {
        this.start = start;
        this.end = end;
        this.content = content;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
