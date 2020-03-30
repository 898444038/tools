package com.ming.tools.test.main;

import com.ming.tools.binding.bean.BeanInfo;
import com.ming.tools.binding.bean.ChangeInfo;
import com.ming.tools.binding.store.cache.EasyCacheUtil;
import com.ming.tools.binding.utils.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2020/1/6 0006.
 */
public class TestApplication {
    public static void main(String[] args) {

        test(1L,"name1");
        test(1L,"name1");
    }

    public static void test(Long id,String name){
        Test t = new Test();
        t.setId(id);
        t.setName(name);
    }
}
