package com.ming.tools.test.main;

import com.ming.tools.binding.anno.Bind;
import com.ming.tools.binding.anno.PrimaryKey;
import com.ming.tools.binding.anno.mapping.Column;
import com.ming.tools.binding.anno.mapping.Comment;
import com.ming.tools.binding.anno.mapping.Table;
import com.ming.tools.binding.enums.MysqlType;

import java.util.Date;

/**
 * Created by Administrator on 2020/1/6 0006.
 */
@Bind
@Table(name = "s_user",aliasName = "u",desc = "user")
public class TestUser {

    @Column
    @PrimaryKey
    private Long id;

    @Column(type = MysqlType.VARCHAR)
    @Comment("名称")
    private String name;

    @Column(type = MysqlType.TEXT)
    @Comment("内容")
    private String content;

    @Column
    @Comment("删除标识")
    private Boolean delFlag;

    @Column
    @Comment("创建时间")
    private Date createTime;

    public TestUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
