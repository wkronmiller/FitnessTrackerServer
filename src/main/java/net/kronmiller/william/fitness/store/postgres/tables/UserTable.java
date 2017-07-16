package net.kronmiller.william.fitness.store.postgres.tables;

import net.kronmiller.william.fitness.entities.User;
import net.kronmiller.william.fitness.exceptions.AppException;
import net.kronmiller.william.fitness.exceptions.DuplicateRecordException;
import net.kronmiller.william.fitness.store.postgres.DatabaseReference;

import java.sql.SQLException;

/**
 * Created by wkronmiller on 7/15/17.
 */
public abstract class UserTable extends Table {
    protected final String uuidTableName;
    protected UserTable(String tableName, String uuidTableName, DatabaseReference database) throws SQLException {
        super(tableName, database);
        this.uuidTableName = uuidTableName;
    }
    public abstract User addUser(User user) throws AppException, SQLException;
    public abstract User getUser(User user) throws AppException, SQLException;
}
