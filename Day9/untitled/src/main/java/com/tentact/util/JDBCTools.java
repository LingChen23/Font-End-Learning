package com.tentact.util;

import java.sql.*;

public class JDBCTools {
    private static String username;
    private static String password;
    private static String url;
    private static String driverClass;

    static {
        url = "jdbc:mysql://localhost:3306/test?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false";
        driverClass = "com.mysql.jdbc.Driver";
        username = "root";
        password = "2002";

        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //获取连接
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }

    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (conn != null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
