package net.kronmiller.william.fitness.RestResponses.Metadata;

import java.io.Serializable;

/**
 * Created by wkronmiller on 7/14/17.
 */
public class ServiceInfo implements Serializable{
    private final String serviceName;
    public ServiceInfo(String serviceName){
        this.serviceName = serviceName;
    }
    public String getServiceName() {
        return this.serviceName;
    }
}
