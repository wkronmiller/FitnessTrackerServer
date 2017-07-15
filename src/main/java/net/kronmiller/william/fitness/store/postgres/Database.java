package net.kronmiller.william.fitness.store.postgres;

import net.kronmiller.william.fitness.configuration.PostgresProperties;
import net.kronmiller.william.fitness.store.DataStore;

import java.sql.*;

/**
 * Created by wkronmiller on 7/15/17.
 */
public abstract class Database implements DataStore {
    private final Connection connection;
    protected Database(PostgresProperties postgresProperties) throws SQLException {
        this.connection = DriverManager.getConnection(postgresProperties.getUrl(),
                postgresProperties.getUsername(),
                postgresProperties.getPassword());
    }
    PreparedStatement prepareStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }
    void commit() throws SQLException {
        connection.commit();
    }
    protected DatabaseReference getReference() {
        return new DatabaseReference(this);
    }
    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

