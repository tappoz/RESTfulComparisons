package org.tappoz.rest.remote.api;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tappoz.rest.remote.data.QuandlPresentationObject;
import org.tappoz.rest.remote.data.QuandlTicker;
import org.tappoz.rest.remote.service.JsonAdapter;
import org.tappoz.rest.remote.service.QuandlAdapter;
import org.tappoz.rest.remote.service.QuandlHttpClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/ticker")
@Produces(MediaType.APPLICATION_JSON)
public class ApiResource {

    private final static Logger log = LoggerFactory.getLogger(ApiResource.class);

    JsonAdapter jsonAdapter;
    QuandlAdapter quandlAdapter;
    QuandlHttpClient quandlHttpClient;

    @Inject
    public ApiResource(JsonAdapter jsonAdapter, QuandlAdapter quandlAdapter, QuandlHttpClient quandlHttpClient) {
        this.jsonAdapter = jsonAdapter;
        this.quandlAdapter = quandlAdapter;
        this.quandlHttpClient = quandlHttpClient;
    }

    @GET
    @Path("{code}")
    public QuandlPresentationObject getTickerFromPathParameter(@PathParam("code") String code) throws IOException {

        QuandlTicker quandlTicker = this.getTicker(code);
        QuandlPresentationObject quandlPresentationObject = quandlAdapter.toPresentationObject(quandlTicker);

        log.info("For this given input ticker '{}', about to return: {}", code, quandlPresentationObject.toString());
        return quandlPresentationObject;
    }

    @GET
    @Timed
    public QuandlPresentationObject getTickerFromQueryParameter(@QueryParam("tickerCode") String tickerCode) throws IOException {

        QuandlTicker quandlTicker = this.getTicker(tickerCode);
        QuandlPresentationObject quandlPresentationObject = quandlAdapter.toPresentationObject(quandlTicker);

        log.info("For this given input ticker '{}', about to return: {}", tickerCode, quandlPresentationObject.toString());
        return quandlPresentationObject;
    }

    protected QuandlTicker getTicker(String tickerCode) throws IOException {

        String remoteJson;
        try {
            remoteJson = quandlHttpClient.getRemoteTicker(tickerCode);
        } catch (Exception e) {
            log.error("An exception {} occurred while attempting to fetch the remote JSON, the exception message was: {}", e.getClass().getCanonicalName(), e.getMessage());
            log.warn("About to return the SAMPLE JSON OBJECT!");
            return jsonAdapter.getSampleQuandlTicker();
        }

        QuandlTicker quandlTicker = jsonAdapter.parseQuandlTicker(remoteJson);
        log.debug("About to return the just parsed JSON object {}", quandlTicker.toString());
        return quandlTicker;
    }
}
