package peaksoft;

import peaksoft.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Asan", "Man", (byte) 23);
        userService.saveUser("Kuba", "Man", (byte) 23);
        userService.saveUser("Nurliza", "Woman", (byte) 23);
        userService.removeUserById(2);
        userService.existsByFirstName("Man");
        System.out.println(userService.existsByFirstName("Man"));
    }
}
