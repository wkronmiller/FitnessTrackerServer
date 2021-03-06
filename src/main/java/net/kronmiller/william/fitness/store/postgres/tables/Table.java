package net.kronmiller.william.fitness.store.postgres.tables;

import net.kronmiller.william.fitness.exceptions.AppException;
import net.kronmiller.william.fitness.store.postgres.DatabaseReference;

import javax.validation.constraints.NotNull;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by wkronmiller on 7/15/17.
 */
public abstract class Table {
    protected final String tableName;
    protected final DatabaseReference database;
    protected abstract String getSchema();
    public void init() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS " +
                tableName +
                getSchema();
        PreparedStatement preparedStatement = this.database.prepareStatement(query);
        preparedStatement.execute();
        this.database.commit();
    }
    protected Table(@NotNull String tableName, @NotNull DatabaseReference database) throws SQLException {
        this.tableName = tableName;
        this.database = database;
    }
}
