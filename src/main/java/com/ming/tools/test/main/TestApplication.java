package com.ming.tools.test.main;

import com.ming.tools.binding.anno.operator.Insert;
import com.ming.tools.binding.anno.operator.Select;
import com.ming.tools.binding.anno.operator.Update;
import com.ming.tools.binding.bean.BeanInfo;
import com.ming.tools.binding.bean.ChangeInfo;
import com.ming.tools.binding.core.ClassHelper;
import com.ming.tools.binding.core.MappingHelper;
import com.ming.tools.binding.core.SqlHelper;
import com.ming.tools.binding.store.cache.EasyCacheUtil;
import com.ming.tools.binding.store.cache.EasyCacheUtil2;
import com.ming.tools.binding.utils.ClassUtil;
import com.ming.tools.binding.utils.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2020/1/6 0006.
 */
public class TestApplication {
    public static void main(String[] args) {
        Class<?> [] classList = {ClassHelper.class};
        for (Class<?> cls: classList) {
            Class clazz = ClassUtil.loadClass(cls.getName(),true);
        }
        Set<Class<?>> classSet = ClassHelper.getBeanClassSet();
        SqlHelper.executeSql(classSet);

        TestUser t1 = insert("name1");
        TestUser t2 = select("name1");
        TestUser t3 = update("name1");

        MappingHelper mappingHelper = new MappingHelper();
        List<String> sqlList = mappingHelper.getSqlString();
//        String insertSql = mappingHelper.getSql(t1.getClass(),"insert");
//        System.out.println(insertSql);
        //test(1L,"name1");
    }

    public static TestUser test(Long id,String name){
        TestUser t = new TestUser();
        t.setId(id);
        t.setName(name);
        return t;
    }

    @Insert
    public static TestUser insert(String name){
        TestUser t = new TestUser();
        t.setName(name);
        return t;
    }

    @Select
    public static TestUser select(String name){
        TestUser t = new TestUser();
        t.setName(name);
        return t;
    }

    @Update
    public static TestUser update(String name){
        TestUser t = new TestUser();
        t.setName(name);
        return t;
    }
}
