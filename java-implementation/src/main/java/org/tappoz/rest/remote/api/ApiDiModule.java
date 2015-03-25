package org.tappoz.rest.remote.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import org.tappoz.rest.remote.service.JsonAdapter;
import org.tappoz.rest.remote.service.JsonValidator;
import org.tappoz.rest.remote.service.QuandlAdapter;
import org.tappoz.rest.remote.service.QuandlHttpClient;

import javax.inject.Singleton;

@Module
    (
        library=true,
        injects = {ApiResource.class, JsonAdapter.class, QuandlAdapter.class, QuandlHttpClient.class, JsonValidator.class}
    )
public class ApiDiModule {

    ApiConfiguration apiConfiguration;

    public ApiDiModule(ApiConfiguration apiConfiguration) {
        this.apiConfiguration = apiConfiguration;
    }

    @Provides @Singleton ObjectMapper providesObjectMapper() {
        return new ObjectMapper();
    }

    @Provides @Singleton QuandlHttpClient providesQuandlHttpClient() {
        return new QuandlHttpClient(this.providesApiConfiguration());
    }

    @Provides @Singleton ApiResource providesApiResource() {
        
        return new ApiResource(this.providesJsonAdapter(), this.providesQuandlAdapter(), this.providesQuandlHttpClient());
    }

    @Provides @Singleton JsonValidator providesJsonValidator() {
        return new JsonValidator(this.providesObjectMapper());
    }

    @Provides @Singleton JsonAdapter providesJsonAdapter() {
        return new JsonAdapter(this.providesObjectMapper());
    }

    @Provides @Singleton QuandlAdapter providesQuandlAdapter() {
        return new QuandlAdapter();
    }

    @Provides @Singleton ApiConfiguration providesApiConfiguration() {
        return this.apiConfiguration;
    }
}
