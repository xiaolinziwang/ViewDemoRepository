package com.chunlin.viewdemo.Eleven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by chunLin on 2017/12/24.
 */

public class ConnectManager {
    ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    public Connection getConnection() {
        Connection connection = threadLocal.get();
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("www.baidu.com");
                threadLocal.set(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public void closeConnection(){
        Connection connection = threadLocal.get();
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

