package com.ming.tools.binding4.test;

import com.ming.tools.binding4.bean.SampleBean;
import com.ming.tools.binding4.core.Binds;
import com.ming.tools.binding4.bind.property.LoggingPropertyChangeListener;
import com.ming.tools.binding4.bind.property.Observable;
import com.ming.tools.binding4.bind.property.ObservableBeanFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 示例
 * https://segmentfault.com/a/1190000021633080
 */
public class PropertyChangeExample {

    private static final Log LOG = LogFactory.getLog(PropertyChangeExample.class);

    public static void main(String[] args) {
        testBindMap();
        System.out.println("==============================");
        testBindMap2();
        System.out.println("==============================");
        testBindObject();
    }

    public static void testBindMap(){
        //绑定有值的map
        Map<String,Object> map = new HashMap<>();
        map.put("a","0");

        map = Binds.bindMap(map);
        map.put("a","a");
        map.put("b","b");
        map.put("b","c");
    }

    public static void testBindMap2(){
        //绑定新new的map
        Map<String,Object> map1 = new HashMap<>();
        map1.put("label","label");
        map1.put("name","name");

        Map<String,Object> map2 = Binds.bindMap();
        map2.put("name","a");
        Binds.copyMap(map1,map2);
        map2.put("name","b");
    }


    public static void test1(){
        SampleBean regular = new SampleBean();
        SampleBean observableBean = ObservableBeanFactory.createObservableBean(SampleBean.class);

        ((Observable) observableBean).addPropertyChangeListener(new LoggingPropertyChangeListener());

        /* 不会被观察到 */
        regular.setStringValue("abc");
        regular.setStringValue("def");
        regular.setIntValue(1);
        regular.setIntValue(2);

        /* 监听 */
        observableBean.setStringValue("zyx");
        observableBean.setStringValue("wvu");
        observableBean.setIntValue(10);
        observableBean.setIntValue(20);
    }

    public static void testBindObject(){
        //insert
        SampleBean regular0 = new SampleBean();
        regular0.setStringValue("abc");
        regular0.setIntValue(10);
        SampleBean regular = Binds.bindObject(SampleBean.class);
        Binds.copy(regular0,regular);

        LOG.info("==============================");

        SampleBean regular3 = Binds.bindObject(SampleBean.class);
        regular3.setStringValue("abc");
        regular3.setStringValue("def");
        regular3.setIntValue(10);
        regular3.setIntValue(20);

        LOG.info("==============================");

        SampleBean regular2 = new SampleBean();
        regular2.setStringValue("a");
        regular2.setIntValue(1);
        regular2 = Binds.bindObject(regular2);
        regular2.setStringValue("b");
        regular2.setIntValue(2);
    }
}
