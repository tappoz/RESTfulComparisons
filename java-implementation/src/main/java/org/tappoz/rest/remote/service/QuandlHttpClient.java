package org.tappoz.rest.remote.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class QuandlHttpClient {

    private final static Logger log = LoggerFactory.getLogger(QuandlHttpClient.class);

    Client httpClient = ClientBuilder.newClient();
    String qualdlBaseUrl = "https://www.quandl.com/api/v1/datasets/WIKI/";

    public String getRemoteTicker(String tickerCode) {

        tickerCode = tickerCode.trim().toUpperCase();
        log.debug("About to perform a remote API call for ticker '{}'", tickerCode);

        URI quandlTickerUri = UriBuilder
                .fromPath(qualdlBaseUrl)
                .path(tickerCode + ".json")
                .queryParam("trim_start", "2015-01-01")
                .queryParam("trim_end", "2015-03-14")
                .queryParam("column", "4") // closing price cfr. Quandl API documentation
                .build();
        log.debug("The URL just built is: {}", quandlTickerUri);

        Response response = httpClient
                .target(quandlTickerUri)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get();

        String jsonObject = response.readEntity(String.class);
        log.debug("The remote JSON resource that has been found is: {}", jsonObject);
        return jsonObject;
    }
}
