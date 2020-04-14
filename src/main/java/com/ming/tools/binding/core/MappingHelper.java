package com.ming.tools.binding.core;

import com.ming.tools.binding.store.cache.EasyCacheUtil;
import com.ming.tools.binding.store.cache.EasyCacheUtil2;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * 实体类与数据库映射
 * Created by Administrator on 2020/4/2 0002.
 */
public class MappingHelper {

    /** 操作常量 */
    public static final String SQL_INSERT = "insert";
    public static final String SQL_UPDATE = "update";
    public static final String SQL_DELETE = "delete";
    public static final String SQL_SELECT = "select";

    private PreparedStatement statement;
    private String sql;
    private Object argType[];
    private ResultSet rs;

    public <T> T select(Object o){
        Class clazz = o.getClass();
        sql = this.getSql(clazz,SQL_SELECT);
        T obj = null;
        try {
            argType = setArgs(o, SQL_SELECT);
            statement = DatabaseHelper.getPreparedStatement(sql);
            statement = DatabaseHelper.setPreparedStatementParam(statement,argType);
            rs = statement.executeQuery();
            Field fields[] = clazz.getDeclaredFields();
            while (rs.next()) {
                obj = (T)clazz.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    fields[i].set(obj, rs.getObject(fields[i].getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public int insert(Object o){
        Class clazz = o.getClass();
        sql = this.getSql(clazz,SQL_INSERT);   //获取sql.
        int i = 0;
        try {
            argType = setArgs(o, SQL_INSERT);
            statement = DatabaseHelper.getPreparedStatement(sql);  //实例化PreparedStatement.
            //为sql语句赋值.
            statement = DatabaseHelper.setPreparedStatementParam(statement,argType);
            i = statement.executeUpdate(); //执行语句.
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseHelper.release(statement, null);  //释放资源.
            return i;
        }
    }

    public int update(Object o){
        Class clazz = o.getClass();
        sql = this.getSql(clazz,SQL_UPDATE);
        int i = 0;
        try {
            argType = setArgs(o, SQL_UPDATE);
            statement = DatabaseHelper.getPreparedStatement(sql);
            statement = DatabaseHelper.setPreparedStatementParam(statement,argType);
            i = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseHelper.release(statement, null);
            return i;
        }
    }

    public int delete(Object o){
        Class clazz = o.getClass();
        sql = this.getSql(clazz,SQL_DELETE);
        int i = 0;
        try {
            argType = this.setArgs(o, SQL_DELETE);
            statement = DatabaseHelper.getPreparedStatement(sql);
            statement = DatabaseHelper.setPreparedStatementParam(statement,argType);
            i = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseHelper.release(statement, null);
            return i;
        }
    }

    public List<String> getSqlString() {
        Map<String,Object> map1 = EasyCacheUtil.getCacheMap();
        Map<String,Object> map2 = EasyCacheUtil2.getCacheMap();
        //insert
        //select
        //update
        return null;
    }


    // sql拼接函数 形如 : insert into User(id,username,password,email,grade) values(?,?,?,?,?)
    public String getSql(Class clazz,String operator) {
        StringBuffer sql = new StringBuffer();
        // 通过反射获取实体类中的所有变量
        Field fields[] = clazz.getDeclaredFields();
        // 插入操作
        if (operator.equals(SQL_INSERT)) {
            sql.append("insert into " + clazz.getSimpleName());
            sql.append("(");
            for (int i = 0; fields != null && i < fields.length; i++) {
                fields[i].setAccessible(true);    //这句话必须要有,否则会抛出异常.
                String column = fields[i].getName();
                sql.append(column).append(",");
            }
            sql = sql.deleteCharAt(sql.length() - 1);
            sql.append(") values (");
            for (int i = 0; fields != null && i < fields.length; i++) {
                sql.append("?,");
            }
            sql.deleteCharAt(sql.length() - 1);
            // 是否需要添加分号
            sql.append(") ");
        } else if (operator.equals(SQL_UPDATE)) {
            sql.append("update " + clazz.getSimpleName() + " set ");
            for (int i = 0; fields != null && i < fields.length; i++) {
                fields[i].setAccessible(true);
                String column = fields[i].getName();
                if (column.equals("id")) {
                    continue;
                }
                sql.append(column).append("=").append("?,");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(" where id=? ");
        } else if (operator.equals(SQL_DELETE)) {
            sql.append("delete from " + clazz.getSimpleName()
                    + " where id=? ");
        } else if (operator.equals(SQL_SELECT)) {
            sql.append("select * from " + clazz.getSimpleName()
                    + " where id=? ");
        }
        System.out.println(sql.toString());
        return sql.toString();
    }

    // 获取参数.
    private Object[] setArgs(Object entity, String operator)
            throws IllegalArgumentException, IllegalAccessException {

        Field fields[] = entity.getClass().getDeclaredFields();
        if (operator.equals(SQL_INSERT)) {

            Object obj[] = new Object[fields.length];
            for (int i = 0; obj != null && i < fields.length; i++) {
                fields[i].setAccessible(true);
                obj[i] = fields[i].get(entity);
            }
            return obj;

        } else if (operator.equals(SQL_UPDATE)) {

            Object Tempobj[] = new Object[fields.length];
            for (int i = 0; Tempobj != null && i < fields.length; i++) {
                fields[i].setAccessible(true);
                Tempobj[i] = fields[i].get(entity);
            }

            Object obj[] = new Object[fields.length];
            System.arraycopy(Tempobj, 1, obj, 0, Tempobj.length - 1);
            obj[obj.length - 1] = Tempobj[0];
            return obj;

        } else if (operator.equals(SQL_DELETE)) {

            Object obj[] = new Object[1];
            fields[0].setAccessible(true);
            obj[0] = fields[0].get(entity);
            return obj;
        } else if (operator.equals(SQL_SELECT)) {

            Object obj[] = new Object[1];
            fields[0].setAccessible(true);
            obj[0] = fields[0].get(entity);
            return obj;
        }
        return null;
    }
}
