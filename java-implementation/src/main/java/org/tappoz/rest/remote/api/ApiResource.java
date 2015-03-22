package org.tappoz.rest.remote.api;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tappoz.rest.remote.data.QuandlPresentationObject;
import org.tappoz.rest.remote.data.QuandlTicker;
import org.tappoz.rest.remote.service.JsonAdapter;
import org.tappoz.rest.remote.service.QuandlAdapter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/ticker")
@Produces(MediaType.APPLICATION_JSON)
public class ApiResource {

    private final static Logger log = LoggerFactory.getLogger(ApiResource.class);

    JsonAdapter jsonAdapter;
    QuandlAdapter quandlAdapter;

    public ApiResource() throws Exception {

        try {
            jsonAdapter = new JsonAdapter();
        } catch (IOException e) {
            log.error("An {} occurred: {}", e.getClass().getCanonicalName(), e);
            throw new Exception("A service can not be initialised! The message is: " + e.getMessage());
        }
        quandlAdapter = new QuandlAdapter();
    }

    public void setJsonAdapter(JsonAdapter jsonAdapter) {
        this.jsonAdapter = jsonAdapter;
    }

    public void setQuandlAdapter(QuandlAdapter quandlAdapter) {
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
