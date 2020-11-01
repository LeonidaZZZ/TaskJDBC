package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    private static Statement statement;
    private static final String TABLE_NAME = "USERS";

    public UserDaoJDBCImpl() {
        statement = Util.connect();
    }

    public void createUsersTable() {
        try  {
            String SQL = "CREATE TABLE " + TABLE_NAME +
                    "(id BIGINT AUTO_INCREMENT, " +
                    " name VARCHAR(50), " +
                    " lastName VARCHAR (50), " +
                    " age TINYINT, " +
                    " PRIMARY KEY (id))";

            statement.executeUpdate(SQL);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            util.close();
        }
    }

    public void dropUsersTable() {
        try {
            String SQL = "DROP TABLE " + TABLE_NAME;
            statement.executeUpdate(SQL);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            util.close();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String SQL = "INSERT " + TABLE_NAME + "(name ,lastName,age)" +
                    "VALUES ('" + name + "','" + lastName + "'," + age + ")";
            statement.executeUpdate(SQL);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            util.close();
        }
    }

    public void removeUserById(long id) {
        try {
            String SQL = "DELETE FROM " + TABLE_NAME +
                    " WHERE id = " + id;
            statement.executeUpdate(SQL);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            util.close();
        }
    }

    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM " + TABLE_NAME + ";";
        List<User> users = new ArrayList<>();

        try (ResultSet result = statement.executeQuery(SQL)) {
            while (result.next()){
                User user = new User();
                user.setId(result.getLong(1));
                user.setName(result.getString(2));
                user.setLastName(result.getString(3));
                user.setAge(result.getByte(4));
                users.add(user);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            util.close();
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            String SQL = "TRUNCATE TABLE " + TABLE_NAME;
            statement.executeUpdate(SQL);
        } catch (SQLException e){
            System.out.println(e.getMessage());

        } finally {
            util.close();
        }
    }
}
