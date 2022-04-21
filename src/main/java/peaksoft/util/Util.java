package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД


    public Connection getConnection() {
        // ushul methoddgo connectino kaitarynyzdar
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                                                "postgres", "PostgresJava");
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to DataBase");
        }
    }
}
