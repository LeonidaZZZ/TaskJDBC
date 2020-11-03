package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

public class Util {
    private static final String CONNECT = "jdbc:mysql://localhost/web?useUnicode=true&serverTimezone=UTC";
    private static final String ROOT = "root";
    private static final String PASSWORD = "AdeptusAstartes@123";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DIALECT = "org.hibernate.dialect.MySQL8Dialect";
    private static Statement statement;
    private static SessionFactory sessionFactory;

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
}

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER,DRIVER);
                settings.put(Environment.URL,CONNECT);
                settings.put(Environment.USER, ROOT);
                settings.put(Environment.PASS,PASSWORD);
                settings.put(Environment.DIALECT,DIALECT);
                settings.put(Environment.SHOW_SQL,"true");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
