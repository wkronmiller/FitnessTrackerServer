package net.kronmiller.william.fitness.store.postgres.tables;

import net.kronmiller.william.fitness.entities.User;
import net.kronmiller.william.fitness.exceptions.AppException;
import net.kronmiller.william.fitness.store.postgres.DatabaseReference;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wkronmiller on 7/15/17.
 *
 * __________________________
 * | User_UUID | user_email |
 * |-----------|------------|
 * |           |            |
 * |___________|____________|
 *
 */
public class UserEmailTable extends UserTable {
    public UserEmailTable(String tableName, String uuidTableName, DatabaseReference database) throws SQLException {
       super(tableName, uuidTableName, database);
    }

    @Override
    public User addUser(User user) throws AppException, SQLException {
        String query = "INSERT INTO " + tableName + "(user_uuid, user_email) VALUES (?, ?)";
        PreparedStatement preparedStatement = database.prepareStatement(query);
        preparedStatement.setInt(1, user.getUuid());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.execute();
        return user;
    }

    private User getUserById(User user) throws SQLException {
        String query = "SELECT (user_email) FROM " + tableName +
                " WHERE user_uuid = ?";
        PreparedStatement preparedStatement = database.prepareStatement(query);
        preparedStatement.setInt(1, user.getUuid());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        user.setEmail(resultSet.getString("user_email"));
        return user;
    }

    private User getUserByEmail(User user) throws SQLException {
        String query = "SELECT (user_uuid) FROM " + tableName +
                " WHERE user_email = ?";
        PreparedStatement preparedStatement = database.prepareStatement(query);
        preparedStatement.setString(1, user.getEmail());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        user.setUuid(resultSet.getInt("user_uuid"));
        return user;
    }

    @Override
    public User getUser(User user) throws AppException, SQLException {
        if(user.getUuid() != null) {
            return getUserById(user);
        } else if(user.getEmail() != null) {
            return getUserByEmail(user);
        }
        throw new AppException("Unable to find user with given information");
    }

    @Override
    protected String getSchema() {
        return "(user_uuid INT PRIMARY KEY," +
                "user_email TEXT NOT NULL UNIQUE," +
                "FOREIGN KEY (user_uuid) REFERENCES " + uuidTableName + " (user_uuid) ON DELETE CASCADE)";
    }
}
