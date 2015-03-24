package org.tappoz.rest.example;

import dagger.Module;
import dagger.Provides;
import io.dropwizard.setup.Environment;
import org.tappoz.rest.example.health.TemplateHealthCheck;
import org.tappoz.rest.example.resources.HelloWorldResource;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
    (
        complete = false,
        injects = {HelloWorldResource.class, TemplateHealthCheck.class},
        library = true
    )
public class HelloWorldModule {

    HelloWorldConfiguration configuration;
    Environment environment;

    public HelloWorldModule(HelloWorldConfiguration configuration, Environment environment) {
        this.configuration = configuration;
        this.environment = environment;
    }

    @Provides @Named("template")  String providesTemplate() {
        return this.configuration.getTemplate();
    }

    @Provides @Named("defaultName")  String providesDefaultName() {
        return this.configuration.getDefaultName();
    }

    @Provides @Singleton HelloWorldResource providesHelloWorldResource() {
        return new HelloWorldResource(
                this.configuration.getTemplate(),
                this.configuration.getDefaultName()
        );
    }

    @Provides @Singleton TemplateHealthCheck providesTemplateHealthCheck() {
        return new TemplateHealthCheck(this.configuration.getTemplate());
    }

}
