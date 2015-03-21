package org.tappoz.rest.example;

import dagger.ObjectGraph;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.tappoz.rest.example.health.TemplateHealthCheck;
import org.tappoz.rest.example.resources.HelloWorldResource;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    ObjectGraph objectGraph;

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
        objectGraph = ObjectGraph.create(new HelloWorldModule(configuration, environment));

        final HelloWorldResource resource = objectGraph.get(HelloWorldResource.class);

        final TemplateHealthCheck healthCheck = objectGraph.get(TemplateHealthCheck.class);
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
