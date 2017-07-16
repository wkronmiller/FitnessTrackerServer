package net.kronmiller.william.fitness.store.postgres;

import net.kronmiller.william.fitness.entities.User;
import net.kronmiller.william.fitness.exceptions.AppException;
import net.kronmiller.william.fitness.exceptions.DuplicateRecordException;
import net.kronmiller.william.fitness.exceptions.InvalidUserException;
import net.kronmiller.william.fitness.exceptions.NotImplementedException;
import net.kronmiller.william.fitness.store.postgres.tables.UserEmailTable;
import net.kronmiller.william.fitness.store.postgres.tables.UserIdTable;
import net.kronmiller.william.fitness.store.postgres.tables.UserNameTable;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;

/**
 * Created by wkronmiller on 7/15/17.
 */
public class UserInformation {
    private final UserIdTable userIdTable;
    private final UserNameTable userNameTable;
    private final UserEmailTable userEmailTable;
    private final DatabaseReference database;
    protected UserInformation(@NotNull String userIdsTableName, @NotNull DatabaseReference database)
            throws SQLException {
        this.userIdTable = new UserIdTable(userIdsTableName,database);
        this.userIdTable.init();
        final String userNamesTableName = "user_names";
        this.userNameTable = new UserNameTable(userNamesTableName, userIdsTableName, database);
        this.userNameTable.init();
        final String userEmailTableName = "user_emails";
        this.userEmailTable = new UserEmailTable(userEmailTableName, userIdsTableName, database);
        this.userEmailTable.init();
        this.database = database;
    }
    public User getUser(int uuid) {
        //TODO
        throw new NotImplementedException();
    }
    public void createUser(User user) throws AppException, SQLException {
        user.validate();
        try {
            user = userIdTable.addUser(user);
            user = userNameTable.addUser(user);
            userEmailTable.addUser(user);
            database.commit();
        } catch(SQLException | AppException e) {
            database.rollBack();
            throw e;
        }
    }
}
