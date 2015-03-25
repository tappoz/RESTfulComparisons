package org.tappoz.rest.remote.service;

import javax.inject.Inject;
import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import org.tappoz.rest.remote.api.ApiConfiguration;

public class QuandlHttpClient {

    private final static Logger log = LoggerFactory.getLogger(QuandlHttpClient.class);

    private ApiConfiguration apiConfiguration;

    private Client httpClient = ClientBuilder.newClient().property(ClientProperties.CONNECT_TIMEOUT, 6000); // usually Quandl response time is around 4500 ms for the following query
    private String qualdlBaseUrl;

    @Inject
    public QuandlHttpClient(ApiConfiguration apiConfiguration) {
        this.apiConfiguration = apiConfiguration;

        this.qualdlBaseUrl = apiConfiguration.getQuandlApiEndPoint();
        log.info("The Quandl remote API URL endpoint has been set to: '{}'", qualdlBaseUrl);
    }

    public String getRemoteTicker(String tickerCode) throws ProcessingException {

        tickerCode = tickerCode.trim().toUpperCase();
        log.debug("About to perform a remote API call for ticker '{}'", tickerCode);

        URI quandlTickerUri = UriBuilder
                .fromPath(qualdlBaseUrl)
                .path(tickerCode + ".json") // e.g. "AAPL.json"
                .queryParam("trim_start", "2015-01-01")
                .queryParam("trim_end", "2015-03-14")
                .queryParam("column", "4") // closing price cfr. Quandl API documentation
                .build();
        log.debug("The URL just built is: {}", quandlTickerUri);

        Response response;

        try {
            response = httpClient
                    .target(quandlTickerUri)
                    .request()
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .get();
        } catch (ProcessingException e) {
            log.error("There might have been a connection timeout, the {} message is: {}", e.getClass().getCanonicalName(), e.getMessage());
            throw e;
        }

        String jsonObject = response.readEntity(String.class);
        log.debug("The remote JSON resource that has been found is: {}", jsonObject);
        return jsonObject;
    }
}
