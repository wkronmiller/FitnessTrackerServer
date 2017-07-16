package net.kronmiller.william.fitness.store.postgres.tables;

import net.kronmiller.william.fitness.entities.User;
import net.kronmiller.william.fitness.exceptions.AppException;
import net.kronmiller.william.fitness.exceptions.DuplicateRecordException;
import net.kronmiller.william.fitness.store.postgres.DatabaseReference;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wkronmiller on 7/15/17.
 *
 * ____________________________________________________
 * | User_UUID | first_name | middle_name | last_name |
 * |-----------|--------------------------------------|
 * |           |                                      |
 * |___________|______________________________________|
 *
 */
public class UserNameTable extends UserTable {
    public UserNameTable(String tableName, String uuidTableName, DatabaseReference database) throws SQLException {
       super(tableName, uuidTableName, database);
    }

    @Override
    public User addUser(User user) throws AppException, SQLException {
        String query = "INSERT INTO " + tableName +
                "(user_uuid, first_name, middle_name, last_name) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = database.prepareStatement(query);
        preparedStatement.setInt(1, user.getUuid());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getMiddleName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.execute();
        return user;
    }

    @Override
    public User getUser(User user) throws AppException, SQLException {
        String query = "SELECT first_name, middle_name, last_name" +
                "FROM " + tableName +
                " WHERE user_uuid = ?";
        PreparedStatement preparedStatement = database.prepareStatement(query);
        preparedStatement.setInt(1, user.getUuid());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        user.setFirstName(resultSet.getString("first_name"));
        user.setMiddleName(resultSet.getString("middle_name"));
        user.setLastName(resultSet.getString("last_name"));
        return user;
    }

    @Override
    protected String getSchema() {
        return "(user_uuid INT PRIMARY KEY," +
                "first_name TEXT NOT NULL," +
                "middle_name TEXT NOT NULL," +
                "last_name TEXT NOT NULL," +
                "FOREIGN KEY (user_uuid) REFERENCES " +
                this.uuidTableName + " (user_uuid) ON DELETE CASCADE)";
    }
}
