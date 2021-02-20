package com.ming.tools.pool;

import com.ming.tools.pool.anno.BindKey;
import com.ming.tools.pool.anno.BindTable;

@BindTable(tableName = "t_menu")
public class Menu {

    @BindKey
    private Long id;

    private String menuname;


    public Menu() {
    }

    public Menu(Long id, String username) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
