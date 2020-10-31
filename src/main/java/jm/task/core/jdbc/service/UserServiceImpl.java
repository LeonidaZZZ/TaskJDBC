package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserServiceImpl extends Util implements UserService {

    public void createUsersTable() throws SQLException {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.createUsersTable();
    }

    public void dropUsersTable() {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        return dao.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.cleanUsersTable();
    }

}
