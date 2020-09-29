package com.ming.tools.pool;

import com.ming.tools.pool.core.BindObject;
import com.ming.tools.pool.core.ObjectPool;

import java.math.BigDecimal;
import java.util.List;

public class ObjectPoolTest {

    public static void main(String[] args) {
        User user = new User();
        user.setId(6L);
//        user.setUsername("zhangsan");
        ObjectPool.getInstance().binding(user);
        user.setAge(27);
        ObjectPool.getInstance().change();
        user.setDelFlag(true);
        ObjectPool.getInstance().change();



        System.out.println();
        //test2();
        test3();
    }

    public static void test3(){
        User user = ObjectPool.getInstance().borrowOneObject(6L);
        user.setPrice(new BigDecimal("540"));
        ObjectPool.getInstance().change();
    }

    public static void test2(){
        User user = new User(1L,"zhangsan");
        ObjectPool.getInstance().binding(user);
        user.setAge(10);
        List<BindObject> list = ObjectPool.getInstance().getAll();
        ObjectPool.getInstance().change();
        List<BindObject> list2 = ObjectPool.getInstance().getAll();
        User user1 = ObjectPool.getInstance().borrowOneObject(1L);
        user1.setDelFlag(false);
        ObjectPool.getInstance().change();
        System.out.println();
    }

    public static void test1(){
        /*com.ming.tools.test.entity.User user1 = new com.ming.tools.test.entity.User(100L,"zhangsan");
        //User user2 = new User(101L,"lisi");

        ObjectListPool.getInstance().addObject(user1);
        com.ming.tools.test.entity.User u1 = (User)ObjectListPool.getInstance().borrowOneObject(100L);
        u1.setUsername("lisi");
        u1.setAge(22);
        u1.setDelFlag(false);
        ObjectListPool.getInstance().returnObject(u1);

        List<ObjectList> lists = ObjectListPool.getInstance().getAll();
        System.out.println();*/
    }
}
