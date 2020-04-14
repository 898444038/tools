package com.ming.tools.binding.core;

import com.ming.tools.binding.anno.Bind;
import com.ming.tools.binding.anno.Constraints;
import com.ming.tools.binding.anno.PrimaryKey;
import com.ming.tools.binding.anno.mapping.Column;
import com.ming.tools.binding.anno.mapping.Comment;
import com.ming.tools.binding.anno.mapping.Table;
import com.ming.tools.binding.bean.BeanColumnInfo;
import com.ming.tools.binding.bean.BeanTableInfo;
import com.ming.tools.binding.bean.MappingInfo;
import com.ming.tools.binding.enums.MysqlType;
import com.ming.tools.binding.store.cache.EasyCacheUtil;
import com.ming.tools.binding.store.cache.EasyCacheUtil2;
import com.ming.tools.binding.utils.ReadFile;
import com.ming.tools.binding.utils.SqlUtil;
import com.ming.tools.binding.utils.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2020/4/1 0001.
 */
public class SqlHelper {

    public static void executeSql(Set<Class<?>> classSet){
        List<String> list = initTable(classSet);
        try {
            new SqlUtil().execute(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取初始化建表语句
     * @param classSet
     * @return
     */
    public static List<String> initTable(Set<Class<?>> classSet){
        Connection con = DatabaseHelper.getConnection();
        ResultSet rs = null;
        List<String> sqlList = new ArrayList<String>();
        try {
            if(!classSet.isEmpty()){
                List<BeanTableInfo> beanTableInfoList = getBeanTableInfoList(classSet);

                for (BeanTableInfo tableInfo : beanTableInfoList){
                    rs = con.getMetaData().getTables(null, null, tableInfo.getTableName(), null);
                    if (rs.next()) {
                        System.out.println("table "+tableInfo.getTableName()+" exist!");
                    }else {
                        System.out.println("table "+tableInfo.getTableName()+" no exist!");
                        String sql = getCreateSQL(tableInfo);
                        System.out.println(sql);
                        sqlList.add(sql);
                    }
                }
                return sqlList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getCreateSQL(BeanTableInfo tableInfo){
        StringBuilder createCommand = new StringBuilder("CREATE TABLE `"+tableInfo.getTableName() +"` (");
        List<BeanColumnInfo> columnInfoList = tableInfo.getColumnInfoList();
        String keyStr = "";
        for(BeanColumnInfo column : columnInfoList){
            createCommand.append("\n");
            createCommand.append("\t`").append(column.getName()).append("` ");
            createCommand.append(column.getType());
            if(column.getLength()!=0){
                createCommand.append("(").append(column.getLength());
                if(column.getPoint()!=0){
                    createCommand.append(",").append(column.getPoint());
                }
                createCommand.append(")");
            }
            createCommand.append(" ");

            if(column.getIsKey()){
                keyStr = "\n\tPRIMARY KEY (`"+column.getName()+"`) USING BTREE";
                createCommand.append("NOT NULL AUTO_INCREMENT").append(" ");
            }else{
                if(column.getDefaultValue()==null){
                    createCommand.append("DEFAULT ");
                    if(column.getType()==MysqlType.BIT){
                        createCommand.append("b'0'");
                    }else{
                        createCommand.append(column.getDefaultValue());
                    }
                    createCommand.append(" ");
                }
            }

            if(StringUtils.isNotEmpty(column.getComment())){
                createCommand.append("COMMENT '").append(column.getComment()).append("' ");
            }
            createCommand.append(",");
        }
        String tableStr = "ENGINE="+tableInfo.getEngine()+" ";
        tableStr+="AUTO_INCREMENT="+tableInfo.getAutoIncrement()+" ";
        tableStr+="DEFAULT CHARSET="+tableInfo.getCharset()+" ";
        tableStr+="ROW_FORMAT="+tableInfo.getRowFormat()+" ";
        tableStr+="COMMENT='"+tableInfo.getDesc()+"'";
        return createCommand+keyStr+" \n )"+tableStr+";";
    }

    public static List<BeanTableInfo> getBeanTableInfoList(Set<Class<?>> classSet) {
        List<BeanTableInfo> beanTableInfoList = new ArrayList<BeanTableInfo>();
        for (Class<?> entity : classSet){
            Bind bind = entity.getAnnotation(Bind.class);
            if(bind == null){
                System.out.println("No Bind annotation in class : "+ entity.getName());
                return null;
            }
            Table table = entity.getAnnotation(Table.class);
            if(table == null){
                System.out.println("No Table annotation in class : "+ entity.getName());
                return null;
            }
            BeanTableInfo beanTableInfo = getBeanTableInfo(entity);
            beanTableInfoList.add(beanTableInfo);
            EasyCacheUtil2.set(entity.hashCode()+"",beanTableInfo);
        }
        return beanTableInfoList;
    }

    public static BeanTableInfo getBeanTableInfo(Class<?> cls) {
        Table table =  cls.getAnnotation(Table.class);
        String tableName = table.name();
        String aliasName = table.aliasName();
        String desc = table.desc();
        String className = getClassName(cls);
        if(StringUtils.isEmpty(tableName)){
            tableName = StringUtils.humpToLine(className);
        }
        if(StringUtils.isEmpty(aliasName)){
            aliasName = StringUtils.getHump(className);
        }
        List<BeanColumnInfo> beanColumnInfoList = new ArrayList<BeanColumnInfo>();
        BeanColumnInfo beanColumnInfo = null;
        for(Field field : cls.getDeclaredFields()){
            if(field.isAnnotationPresent(Column.class)){
                Column column = field.getAnnotation(Column.class);
                Comment comment = field.getAnnotation(Comment.class);
                String columnName = column.name();
                if(StringUtils.isEmpty(columnName)){
                    columnName = StringUtils.humpToLine(field.getName());
                }
                MysqlType columnType = column.type();

                Class property = MysqlType.getClazz(column.type());
                if(property==Object.class){
                    if(columnType == MysqlType.AUTO_TYPE){
                        MysqlType type = MysqlType.getMysqlType(field.getType());
                        property = type.getClazz();
                        columnType = type;
                    }
                }

                String defaultValue = column.defaultValue();
                if(StringUtils.isEmpty(defaultValue)){
                    defaultValue = (String) columnType.getDefaultValue();
                }
                int length = column.length();
                if(length==0){
                    length = columnType.getLength();
                }
                int point = column.point();
                if(point==0){
                    point = columnType.getPoint();
                }
                boolean isNull = column.isNull();
                boolean isKey = field.isAnnotationPresent(PrimaryKey.class);
                if(isKey){
                    isNull = false;
                }
                String commentStr = null;
                if(comment!=null){
                    commentStr = comment.value();
                }

                beanColumnInfo = new BeanColumnInfo();
                beanColumnInfo.setName(columnName);
                beanColumnInfo.setType(columnType);
                beanColumnInfo.setProperty(property);
                beanColumnInfo.setDefaultValue(defaultValue);
                beanColumnInfo.setLength(length);
                beanColumnInfo.setPoint(point);
                beanColumnInfo.setIsNull(isNull);
                beanColumnInfo.setIsKey(isKey);
                beanColumnInfo.setComment(commentStr);
                beanColumnInfoList.add(beanColumnInfo);
            }
        }

        BeanTableInfo beanTableInfo = new BeanTableInfo();
        beanTableInfo.setTableName(tableName);
        beanTableInfo.setAliasName(aliasName);
        beanTableInfo.setDesc(desc);
        beanTableInfo.setEngine(table.engine());
        beanTableInfo.setAutoIncrement(table.autoIncrement());
        beanTableInfo.setCharset(table.charset());
        beanTableInfo.setRowFormat(table.rowFormat());
        beanTableInfo.setColumnInfoList(beanColumnInfoList);
        return beanTableInfo;
    }

    //获取类名，不带包名
    private static String getClassName(Class clazz){
        String name = clazz.getName();
        String[] names = name.split("\\.");
        return names[names.length-1];
    }

    /**
     * 获得约束条件
     * @param con
     * @return
     */
    private static String getConstraints(Constraints con){
        String constraints = "";
        if(!con.allowNull()){
            constraints += " NOT NULL";
        }
        if(con.primaryKey()){
            constraints += " PRIMARY KEY";
        }
        if(con.unique()){
            constraints += " UNIQUE ";
        }
        return constraints;
    }

    /**
     * 获得所需要的字段
     * @param fields
     * @return
     */
    private static List<Field> getNeedField(Field[] fields){
        List<Field> allFileds = new ArrayList<Field>();
        for(Field field : fields){
            // 获得每个字段上的注解信息,这里不需要继承的注解
            Annotation[] anns = field.getDeclaredAnnotations();
            if(anns.length != 0){
                // 如果该字段没有注解，表示这个字段，不需要生成
                allFileds.add(field);
            }
        }
        return allFileds;
    }

}
