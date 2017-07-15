package net.kronmiller.william.fitness.store.postgres.tables;

import net.kronmiller.william.fitness.store.postgres.DatabaseReference;

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
public class UserNameTable extends Table {
    private final String uuidTableName;
    public UserNameTable(String tableName, String uuidTableName, DatabaseReference database) throws SQLException {
       super(tableName, database);
       this.uuidTableName = uuidTableName;
    }
    @Override
    protected String getSchema() {
        return "(user_uuid INT PRIMARY KEY," +
                "fist_name TEXT NOT NULL," +
                "middle_name TEXT NOT NULL," +
                "last_name TEXT NOT NULL," +
                "FOREIGN KEY (user_uuid) REFERENCES " + uuidTableName + " (user_uuid))";
    }
}
