package net.kronmiller.william.fitness.store.postgres.tables;

import net.kronmiller.william.fitness.store.postgres.DatabaseReference;

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
public class UserIdTable extends Table {
    public UserIdTable(String tableName, DatabaseReference database) throws SQLException {
       super(tableName, database);
    }
    @Override
    protected String getSchema() {
        return "(user_uuid INT PRIMARY KEY)";
    }
}
