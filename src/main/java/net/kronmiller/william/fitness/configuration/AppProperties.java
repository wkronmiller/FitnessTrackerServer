package net.kronmiller.william.fitness.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by wkronmiller on 7/14/17.
 */
@ConfigurationProperties(prefix="app")
@Component
public class AppProperties {
    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
