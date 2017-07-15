package net.kronmiller.william.fitness.store.postgres;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Database reference to be passed to Tables
 */
public class DatabaseReference {
    private DatabaseReference() {}
    private Database database;
    DatabaseReference(Database database) {
        this.database = database;
    }
    public PreparedStatement prepareStatement(String query) throws SQLException {
        return this.database.prepareStatement(query);
    }
    public void commit() throws SQLException {
        this.database.commit();
    }
}
