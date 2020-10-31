package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("John", "Malkovich", (byte) 40);
        userService.saveUser("Deckard", "Kain", (byte) 80);
        userService.saveUser("Sam", "Fisher", (byte) 35);
        userService.saveUser("Geralt", "FromRivia", (byte) 120);
        List<User> users = userService.getAllUsers();
        users.forEach(System.out::println);
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
