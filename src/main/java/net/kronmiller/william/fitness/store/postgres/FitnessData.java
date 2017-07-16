package net.kronmiller.william.fitness.store.postgres;

import net.kronmiller.william.fitness.configuration.PostgresProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

interface FitnessTables {
    String USER_IDS_TABLE_NAME = "user_ids";
}
/**
 * Created by wkronmiller on 7/15/17.
 */
public class FitnessData extends Database implements FitnessTables {
    private final UserInformation userInformation;
    public FitnessData(PostgresProperties postgresProperties) throws SQLException, ClassNotFoundException {
        super(postgresProperties);
        this.userInformation = new UserInformation(USER_IDS_TABLE_NAME, this.getReference());
    }
    public UserInformation getUserInformation() {
       return userInformation;
    }
}
