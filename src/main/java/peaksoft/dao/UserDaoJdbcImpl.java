package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    private final Connection connection;

    public UserDaoJdbcImpl() throws SQLException {

        connection = new Util().getConnection();


    }

    public void createUsersTable() {

        String query = """
                            create table if not exists users (
                            
                            id serial primary key,
                            name varchar not null,
                            last_name varchar not null,
                            age smallint not null
                            
                            ) ;
                            """;
        try (Statement statement = connection.createStatement();){
            statement.execute(query);
            System.out.println("Successful Created...");


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void dropUsersTable() {
        String queryDropTable = "drop table users";
        try (Statement statement = connection.createStatement();){
            statement.execute(queryDropTable);
            System.out.println("Table successfully deleted");

        } catch (SQLException e) {
            throw new RuntimeException("No table to delete");
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        String saveQuery = """
                                insert into users (name, last_name, age) values 
                                                (?, ?, ?);
                                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(saveQuery);){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("Successful saved");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void removeUserById(long id) {
        String removeByidquery = """
                                     delete from users where id = ?;
                                     """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(removeByidquery); ){
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            System.out.println("Successful removed by " + id);


        } catch (SQLException e) {
            throw new RuntimeException("Failed cant romove ");
        }


    }

    public List<User> getAllUsers() {
        String getAllUserQuery = "select * from users;";
        List<User> allUsers = new ArrayList<>();

        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllUserQuery))
        {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte(4));
                allUsers.add(user);
            }

        }catch (SQLException e) {
            throw new RuntimeException("Cant get all users");
        }

        return allUsers;
    }

    public void cleanUsersTable() {
        String cleanQuery = "truncate table users;";
        try (Statement statement = connection.createStatement(); ){
            statement.execute(cleanQuery);
            System.out.println(" you have successfully deleted all Users in  table");
        } catch (SQLException e) {
            throw new RuntimeException("Cant truncate table");
        }


    }

    public boolean existsByFirstName(String firstName) {
        // eger databasede parametrine kelgen firstnamege okshosh adam bar bolso
        // anda true kaitarsyn
        // jok bolso anda false kaitarsyn.

        String query = "select case when name notnull then true else false end from users where name like ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, firstName);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
}
}