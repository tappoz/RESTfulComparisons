package org.tappoz.rest.remote.api;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tappoz.rest.remote.data.QuandlPresentationObject;
import org.tappoz.rest.remote.data.QuandlTicker;
import org.tappoz.rest.remote.service.JsonAdapter;
import org.tappoz.rest.remote.service.QuandlAdapter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/ticker")
@Produces(MediaType.APPLICATION_JSON)
public class ApiResource {

    private final static Logger log = LoggerFactory.getLogger(ApiResource.class);

    JsonAdapter jsonAdapter;
    QuandlAdapter quandlAdapter;

    @Inject
    public ApiResource(JsonAdapter jsonAdapter, QuandlAdapter quandlAdapter) {
        this.jsonAdapter = jsonAdapter;
        this.quandlAdapter = quandlAdapter;
    }

    @GET
    @Path("{code}")
    public QuandlPresentationObject getTickerFromPathParameter(@PathParam("code") String code) {

        // TODO implement the remote retrieval... for now fetch the sample JSON object
        QuandlTicker quandlTicker = jsonAdapter.getSampleQuandlTicker();
        QuandlPresentationObject quandlPresentationObject = quandlAdapter.toPresentationObject(quandlTicker);

        log.info("For this given input ticker '{}', about to return: {}", code, quandlPresentationObject.toString());
        return quandlPresentationObject;
    }

    @GET
    @Timed
    public QuandlPresentationObject getTickerFromQueryParameter(@QueryParam("tickerCode") String tickerCode) {

        // TODO implement the remote retrieval... for now fetch the sample JSON object
        QuandlTicker quandlTicker = jsonAdapter.getSampleQuandlTicker();
        QuandlPresentationObject quandlPresentationObject = quandlAdapter.toPresentationObject(quandlTicker);

        log.info("For this given input ticker '{}', about to return: {}", tickerCode, quandlPresentationObject.toString());
        return quandlPresentationObject;
    }
}
