package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("John", "Malkovich", (byte) 40);
        userService.saveUser("Deckard", "Kain", (byte) 80);
        userService.saveUser("Sam", "Fisher", (byte) 35);
        userService.saveUser("Geralt", "FromRivia", (byte) 120);
        userService.removeUserById(4);
        List<User> list = userService.getAllUsers();
        list.forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
