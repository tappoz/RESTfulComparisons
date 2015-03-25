package org.tappoz.rest.remote.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class ApiConfiguration extends Configuration {

    @NotEmpty
    @JsonProperty
    private String quandlApiEndPoint;

    @NotEmpty
    @JsonProperty
    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getQuandlApiEndPoint() {
        return quandlApiEndPoint;
    }

    public void setQuandlApiEndPoint(String quandlApiEndPoint) {
        this.quandlApiEndPoint = quandlApiEndPoint;
    }
}
