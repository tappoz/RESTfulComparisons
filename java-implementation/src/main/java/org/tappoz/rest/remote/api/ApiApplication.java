package org.tappoz.rest.remote.api;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiApplication extends Application<ApiConfiguration>{

    private final static Logger log = LoggerFactory.getLogger(ApiApplication.class);

    public static void main(String[] args) throws Exception {

        Application apiApplication = new ApiApplication();
        log.info("About to start the {}", ApiApplication.class.getCanonicalName());
        apiApplication.run(args);
        log.info("Just started the {}", ApiApplication.class.getCanonicalName());
    }

    @Override
    public void run(ApiConfiguration configuration, Environment environment) throws Exception {
        final ApiResource apiResource = new ApiResource();
        environment.jersey().register(apiResource);
    }
}
