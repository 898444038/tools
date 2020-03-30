package com.ming.tools.binding.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Set;

/**
 * 对象比较器
 * Created by Administrator on 2020/3/18 0018.
 */
public class ObjectComparator {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ParseException, IntrospectionException {
        //		Log log = new Log();
        //		log.setId("f7afefe497f1428da91c3e836e9a047b");
        //		deleteOutCycleLog();

        CapFilesDemo c = new CapFilesDemo();
        c.setFilename("我");
        c.setFilesrc("你好");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        c.setSd(sdf.parse("2018年10月12日"));
        CapFilesDemo c2 = new CapFilesDemo();
        c2.setFilename("我");
        c2.setFilesrc("你好");
        c2.setSd(sdf.parse("2018年10月15日"));



        /*Map<String,LogValue> oldMap= getValuesToMap(c);
        Map<String,LogValue> newMap= getValuesToMap(c2);
        StringBuffer sb = comparatorObject(oldMap, newMap);
        System.out.println(sb.toString());*/
    }

    /**
     * 对象比较器
     * @param oldMap 原对象参数
     * @param newMap 现对象参数
     * @return 返回比较之后的结果
     */
    private static StringBuffer comparatorObject(
            Map<String, LogValue> oldMap, Map<String, LogValue> newMap) {
        StringBuffer sb = new StringBuffer();
        /*if(oldMap!=null&&!oldMap.isEmpty()){
            Set<Map.Entry<String,LogValue>> comparSet= oldMap.entrySet();
            for (Map.Entry<String, LogValue> entry : comparSet) {
                LogValue newVo = newMap.get(entry.getKey());
                LogValue oldVo = entry.getValue();
                Object newValue = newVo.getValue();
                Object oldValue =  oldVo.getValue();
                Class<?> type = newValue.getClass();
                String typeName = newValue.getClass().getName();
                if (newVo!=null) {
                    if ("java.lang.String".equals(typeName)) {
                        String newStr = (String)newValue;
                        String oldStr = (String)oldValue;
                        if (!StringUtils.equals(newStr, oldStr)) {
                            sb.append(showMsg(newVo, newStr, oldStr));
                        }
                    }else if ("java.sql.Timestamp".equals(typeName)) {
                        DateFormat format = new SimpleDateFormat(StringUtils.isBlank(newVo.getFormat())?"yyyy-MM-dd HH:mm:ss":newVo.getFormat());
                        java.sql.Timestamp newTime = (java.sql.Timestamp)newValue;
                        java.sql.Timestamp oldTime = (java.sql.Timestamp)oldValue;
                        String newTempTimeStr = "";
                        String oldTimeTimeStr = "";
                        if (newTime!=null) {
                            newTempTimeStr = format.format(newTime);
                        }
                        if(oldTime!=null)  {
                            oldTimeTimeStr = format.format(oldTime);
                        }
                        if (!StringUtils.equals(newTempTimeStr, oldTimeTimeStr)) {
                            sb.append(showMsg(newVo, format.format(newTime), format.format(oldTime)));
                        }
                    }else if ("java.lang.Long".equals(typeName) || Long.TYPE == type) {
                        java.lang.Long newLog = NumberUtils.createLong(NumberUtils.isNumber(newValue+"")?newValue+"":"0");
                        java.lang.Long oldLog = NumberUtils.createLong(NumberUtils.isNumber(oldValue+"")?oldValue+"":"0");
                        if(newLog.compareTo(oldLog)!=0){
                            sb.append(showMsg(newVo, newLog, oldLog));
                        }
                    }else if ("java.lang.Integer".equals(typeName) || Integer.TYPE == type) {
                        java.lang.Integer newInt = NumberUtils.createInteger(NumberUtils.isNumber(newValue+"")?newValue+"":"0");
                        java.lang.Integer oldInt = NumberUtils.createInteger(NumberUtils.isNumber(oldValue+"")?oldValue+"":"0");
                        if(newInt.compareTo(oldInt)!=0){
                            sb.append(showMsg(newVo, newInt, oldInt));
                        }
                    }else if ("java.lang.Boolean".equals(typeName) || Boolean.TYPE == type) {
                        java.lang.Boolean newbool = BooleanUtils.toBoolean(newValue+"")?true:false;
                        java.lang.Boolean oldbool = BooleanUtils.toBoolean(oldValue+"")?true:false;
                        if(newbool!=oldbool){
                            sb.append(showMsg(newVo, newbool, oldbool));
                        }
                    } else if ("java.lang.Character".equals(typeName)
                            || Character.TYPE == type) {
                        // 预留
                    } else if ("java.lang.Byte".equals(typeName) || Byte.TYPE == type) {
                        //预留不处理
                    } else if ("java.lang.Short".equals(typeName) || Short.TYPE == type) {
                        //预留不处理 有需要在处理
                    } else if ("java.lang.Float".equals(typeName) || Float.TYPE == type) {
                        java.lang.Float newFloat = NumberUtils.createFloat(NumberUtils.isNumber(newValue+"")?newValue+"":"0");
                        java.lang.Float oldFloat = NumberUtils.createFloat(NumberUtils.isNumber(oldValue+"")?oldValue+"":"0");
                        if(newFloat.compareTo(oldFloat)!=0){
                            sb.append(showMsg(newVo, newFloat, oldFloat));
                        }
                    } else if ("java.lang.Double".equals(typeName) || Double.TYPE == type) {
                        java.lang.Double newDouble = NumberUtils.createDouble(NumberUtils.isNumber(newValue+"")?newValue+"":"0");
                        java.lang.Double oldDouble = NumberUtils.createDouble(NumberUtils.isNumber(oldValue+"")?oldValue+"":"0");
                        if(newDouble.compareTo(oldDouble)!=0){
                            sb.append(showMsg(newVo, newDouble, oldDouble));
                        }
                    } else if ("java.util.Date".equals(typeName)) {
                        DateFormat format = new SimpleDateFormat(StringUtils.isBlank(newVo.getFormat())?"yyyy-MM-dd":newVo.getFormat());
                        java.util.Date newTime = (java.util.Date)newValue;
                        java.util.Date oldTime = (java.util.Date)oldValue;
                        String newTempTimeStr = "";
                        String oldTimeTimeStr = "";
                        if (newTime!=null) {
                            newTempTimeStr = format.format(newTime);
                        }
                        if(oldTime!=null)  {
                            oldTimeTimeStr = format.format(oldTime);
                        }
                        if (!StringUtils.equals(newTempTimeStr, oldTimeTimeStr)) {
                            sb.append(showMsg(newVo, format.format(newTime), format.format(oldTime)));
                        }
                    }
                }
            }
        }*/
        return sb;
    }


    /**
     * 页面标记
     * @param newVo
     * @param newLog
     * @param oldLog
     * @return
     */
    private static String showMsg(LogValue newVo, Object newLog,
                                  Object oldLog) {
        return "["+newVo.getName()+"]发生变化由原先的:"+oldLog+"改变为:"+newLog+"<br>";
    }
    /**
     * 将要转化的对象进行拆分为Map<String,LogValue> LogValue 保存自定义标签的名称 和 数值等内容
     * @param entity
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IntrospectionException
     */
    /*private static Map<String,LogValue> getValuesToMap(Object entity)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException, IntrospectionException {
        Map<String,LogValue> comparMap = new HashedMap();
        java.lang.reflect.Field[] fields = entity.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            if(PropertyUtils.isReadable(entity, name) && PropertyUtils.isWriteable(entity, name)) {
                if(fields[i].isAnnotationPresent(LogCompar.class)){
                    LogCompar logVo = fields[i].getAnnotation(LogCompar.class);
                    LogValue lv = new LogValue(logVo.name(),getProperties(entity, name),logVo.dateFormat());
                    comparMap.put(name, lv);
                }
            }
        }
        return comparMap;
    }*/

    /**
     * 利用内省机制来获取参数
     * @param b
     * @param name
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static Object getProperties(Object b, String name)
            throws IntrospectionException, IllegalAccessException,
            InvocationTargetException {
	        /*
	        PropertyDescriptor p = new PropertyDescriptor(name, b.getClass());
	        //通过内省的方法来执行制定字段的get方法
	        Method mathGet = p.getReadMethod();
	        mathGet.setAccessible(true);
	        Object age = mathGet.invoke(b);*/

        BeanInfo beanInfo = Introspector.getBeanInfo(b.getClass());
        PropertyDescriptor[] mathAll = beanInfo.getPropertyDescriptors();
        Object value = null;
        for(PropertyDescriptor pd:mathAll){
            if(pd.getName().equals(name)){
                Method mathGet = pd.getReadMethod();
                mathGet.setAccessible(true);
                value = mathGet.invoke(b);
            }
        }

        return value;
    }

}
