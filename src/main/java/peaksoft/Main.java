package peaksoft;

import peaksoft.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Man", "Woman", (byte) 23);
        userService.saveUser("Michael", "Man", (byte) 23);
        userService.saveUser("Jackson", "Woman", (byte) 23);
        userService.removeUserById(2);
        userService.existsByFirstName("Man");
        System.out.println(userService.existsByFirstName("Man"));
    }
}
