package com.ming.tools.binding.utils;

import java.util.Date;

/**
 * Created by Administrator on 2020/3/18 0018.
 */
public class CapFilesDemo /*extends DataEntity<CapFilesDemo>*/ {

    private static final long serialVersionUID = 1L;
    @LogCompar(name="文件名")
    private String filename;		// 文件名
    @LogCompar(name="地址")
    private String filesrc;		// 文件地址
    @LogCompar(name="日期验证",dateFormat="yyyy年MM月dd日")
    private Date sd;


    public CapFilesDemo() {
        super();
    }

    public CapFilesDemo(String id){
        //super(id);
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilesrc() {
        return filesrc;
    }

    public void setFilesrc(String filesrc) {
        this.filesrc = filesrc;
    }

    public Date getSd() {
        return sd;
    }

    public void setSd(Date sd) {
        this.sd = sd;
    }
}
