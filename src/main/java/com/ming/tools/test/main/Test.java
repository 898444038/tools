package com.ming.tools.test.main;

import com.ming.tools.binding.anno.Bind;
import com.ming.tools.binding.anno.PrimaryKey;

/**
 * Created by Administrator on 2020/1/6 0006.
 */
//@GenerateScan(basePackages = {"com.ming.tools.test"})
@Bind
public class Test {
    @PrimaryKey
    private Long id;

    private String name;

    public Test() {
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

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
