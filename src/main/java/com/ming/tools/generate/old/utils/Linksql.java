package com.ming.tools.generate.old.utils;

import java.sql.*;

public class Linksql {

    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //指定连接数据库的url
    final static String DB_URL = "jdbc:mysql://localhost/student";
    //mysql用户名
    final static String name = "root";
    //mysql密码
    final static String pwd = "pwd";

    //获取连接方法
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //释放资源方法
    public static void release(Connection conn, Statement pstmt, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //建表
    public void createTable(String creatsql){
        Connection conn = null;
        Statement stmt  = null;
        ResultSet rs = null;
        try {
            //1、获取连接
            conn = Linksql.getConnection();
            //3、获取执行语句对象
            stmt = conn.createStatement();
            if(0 == stmt.executeLargeUpdate(creatsql)) {
                System.out.println("成功创建表！");
            } else {
                System.out.println("创建表失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //7、释放资源
            Linksql.release(conn, stmt, rs);
        }
    }

    //判断表是否存在
    public boolean validateTableNameExist(String tableName) throws Exception {
        Connection con = getConnection();
        ResultSet rs = con.getMetaData().getTables(null, null, tableName, null);
        if (rs.next()) {
            return true;
        }else {
            return false;
        }
    }
}