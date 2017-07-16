package net.kronmiller.william.fitness.store.postgres.tables;

import net.kronmiller.william.fitness.entities.User;
import net.kronmiller.william.fitness.exceptions.AppException;
import net.kronmiller.william.fitness.exceptions.DuplicateRecordException;
import net.kronmiller.william.fitness.store.postgres.DatabaseReference;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wkronmiller on 7/15/17.
 *
 * De-couple user IDs from everything else, because uniquely identifying people by their names is a pain in the ass
 * and we'll probably do it wrong hte first few times
 * _____________
 * | User_UUID |
 * |-----------|
 * |           |
 * |___________|
 */
public class UserIdTable extends UserTable {
    public UserIdTable(String tableName, DatabaseReference database) throws SQLException {
       super(tableName, tableName, database);
    }
    @Override
    protected String getSchema() {
        return "(user_uuid SERIAL PRIMARY KEY)";
    }

    @Override
    public User addUser(User user) throws DuplicateRecordException, SQLException {
        if(user.getUuid() != null) {
            throw new DuplicateRecordException();
        }
        String query = "INSERT INTO " + tableName + " (user_uuid) VALUES (DEFAULT) RETURNING user_uuid";
        ResultSet resultSet = database.prepareStatement(query).executeQuery();
        resultSet.next();
        Integer uuid = resultSet.getInt("user_uuid");
        user.setUuid(uuid);
        return user;
    }

    @Override
    public User getUser(User user) throws AppException, SQLException {
        //No-Op
        return user;
    }
}
