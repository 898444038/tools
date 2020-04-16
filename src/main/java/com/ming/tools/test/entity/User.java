package com.ming.tools.test.entity;

import com.ming.tools.generate.old.annotation.Column;
import com.ming.tools.generate.old.annotation.Description;
import com.ming.tools.generate.old.annotation.GenerateMybatis;
import com.ming.tools.generate.old.annotation.NotNull;
import com.ming.tools.generate.old.annotation.PrimaryKey;
import com.ming.tools.generate.old.annotation.Text;
import com.ming.tools.generate.old.enums.ColumnType;

import java.util.Date;

/**
 * Created by Administrator on 2020/1/6 0006.
 */
@GenerateMybatis(tableName = "s_user")
@Description(author = "wang",desc = "用户")
public class User {

    @Column
    @PrimaryKey
    //@Comment("主键")
    private Long id;

    @NotNull
    //@Comment("用户名")
    @Column(name = "user_name",type = ColumnType.VARCHAR,length = 32)
    private String username;

    @Text
    private String content;

    private Boolean delFlag;

    private Date time;

    private Integer age;

    private Double price;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
