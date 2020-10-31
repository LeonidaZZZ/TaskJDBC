package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String CONNECT = "jdbc:mysql://localhost/web?useUnicode=true&serverTimezone=UTC";
    private static final String ROOT = "root";
    private static final String PASSWORD = "AdeptusAstartes@123";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Statement statement;

    public static Statement connect() {

        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(CONNECT, ROOT, PASSWORD);
            statement = connection.createStatement();
            System.out.println("Connection OK");
        }catch (ClassNotFoundException|SQLException e){
            System.out.print("Connection ERR - ");
            System.out.println(e.getMessage());
        }
        return statement;
    }

    public void close(){
        try {
            if (statement != null) {
                statement.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e){
            System.out.print("Close ERR - ");
            System.out.println(e.getMessage());
    }
// реализуйте настройку соеденения с БД
}
}
