package net.kronmiller.william.fitness;

import net.kronmiller.william.fitness.RestResponses.Metadata.ServiceInfo;
import net.kronmiller.william.fitness.configuration.AppProperties;
import net.kronmiller.william.fitness.configuration.PostgresProperties;
import net.kronmiller.william.fitness.entities.User;
import net.kronmiller.william.fitness.exceptions.AppException;
import net.kronmiller.william.fitness.store.postgres.FitnessData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * Created by wkronmiller on 7/14/17.
 */
@RestController
@RequestMapping(value="/api/fitness/v0")
@EnableConfigurationProperties(AppProperties.class)
public class FitnessRestController {
    private final FitnessData fitnessData;
    @Autowired
    public FitnessRestController(PostgresProperties postgresProperties) throws SQLException, ClassNotFoundException {
        this.fitnessData = new FitnessData(postgresProperties);
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String root() {
        return "v0"; //TODO
    }

    @RequestMapping(value="/users", method = RequestMethod.POST)
    public Boolean addUser(User user) {
        System.out.println("Adding user: " + user);
        try {
            fitnessData.getUserInformation().createUser(user);
        } catch (AppException | SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @RequestMapping(value="/users/example", method = RequestMethod.GET)
    public User getExampleUser() {
        return new User();//TODO
    }
}
