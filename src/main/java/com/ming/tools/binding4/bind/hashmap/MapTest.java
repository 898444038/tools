package com.ming.tools.binding4.bind.hashmap;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("name", "A");
        map = MapProxy.newProxy(map);
        map.put("name", "b");
        map.put("name", "c");
        map.put("name2", "a");
    }

}

