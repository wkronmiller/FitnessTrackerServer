package net.kronmiller.william.fitness;

import net.kronmiller.william.fitness.RestResponses.Metadata.ServiceInfo;
import net.kronmiller.william.fitness.configuration.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wkronmiller on 7/14/17.
 */
@RestController
@RequestMapping(value="/")
@EnableConfigurationProperties(AppProperties.class)
public class RootRestController {
    private final AppProperties appProperties;

    @Autowired
    public RootRestController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public ServiceInfo root() {
        return new ServiceInfo(appProperties.getName());
    }
}
