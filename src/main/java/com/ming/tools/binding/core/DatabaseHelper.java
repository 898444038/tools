package com.ming.tools.binding.core;

import com.ming.tools.binding.utils.PropsUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 第一步
 * 数据库操作助手类
 * 提供事务相关操作
 */
public class DatabaseHelper {
    //private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();

    static {
        Properties conf = PropsUtil.loadProps("ming.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("can not load jdbc driver");
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库连接
     */
 /*   public static Connection getConnection() {
        Connection conn = CONNECTION_HOLDER.get();//1
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                LOGGER.error("get connection failure", e);
            } finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
        return conn;
    }*/

    /**
     * 关闭数据库连接
     */
 /*   public static void closeConnection() {
        Connection conn = CONNECTION_HOLDER.get();//1
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.remove();//3
            }
        }
    }
*/
    /**
     * 开启事务
     */
    public static void beginTransaction() {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                System.out.println("begin transaction failure");
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.set(connection);
            }
        }
    }

    /**
     * 提交事务
     */
    public static void commitTransaction() {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                System.out.println("commit transaction failure");
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.remove();
            }

        }
    }

    /**
     * 回滚事务
     */
    public static void rollbackTransaction() {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException e) {
                System.out.println("rollback transaction failure");
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.remove();
            }

        }
    }


    private static Connection con;

    // 获取数据库连接对象
    public static Connection getConnection() {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            return con;
        }
        return con;
    }

    public static PreparedStatement getPreparedStatement(String sql)
            throws SQLException {

        return getConnection().prepareStatement(sql);
    }

    public static PreparedStatement setPreparedStatementParam(
            PreparedStatement statement, Object obj[]) throws SQLException {

        for (int i = 0; i < obj.length; i++) {
            statement.setObject(i + 1, obj[i]);
        }
        return statement;
    }

    // 释放资源
    public static void release(PreparedStatement ps, ResultSet rs) {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
            if (ps != null) {
                ps.close();
                ps = null;
            }
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}