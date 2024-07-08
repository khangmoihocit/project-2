package com.javabykhang.project2pf.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//utils: Xử lý các thao tác bị lặp lại
public class ConnectionJDBCUtil {
    static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    static final String USER = "root";
    static final String PASS = "root";

    public static Connection getConnection (){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return connection;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
