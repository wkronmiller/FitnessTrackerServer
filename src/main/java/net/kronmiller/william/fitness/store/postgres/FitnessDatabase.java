package net.kronmiller.william.fitness.store.postgres;

import net.kronmiller.william.fitness.configuration.PostgresProperties;
import net.kronmiller.william.fitness.store.postgres.tables.UserIdTable;
import net.kronmiller.william.fitness.store.postgres.tables.UserNameTable;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

/**
 * Created by wkronmiller on 7/15/17.
 */
public class FitnessDatabase extends Database {
    private final String DATABASE_NAME = "fitness_tracker";
    private final UserIdTable userIdTable;
    private final UserNameTable userNameTable;
    @Autowired
    public FitnessDatabase(PostgresProperties postgresProperties) throws SQLException {
        super(postgresProperties);
        final String userIdTableName = DATABASE_NAME + ".user_ids";
        this.userIdTable = new UserIdTable(userIdTableName,
                this.getReference());
        final String userNamesTableName = DATABASE_NAME + ".user_names";
        this.userNameTable = new UserNameTable(userNamesTableName,
                userIdTableName,
                this.getReference());
    }

    @Override
    public void init() {
        //TODO
        throw new RuntimeException("Not implemented");
    }
}
