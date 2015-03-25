package org.tappoz.rest.remote.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tappoz.rest.remote.data.QuandlPresentationObject;
import org.tappoz.rest.remote.data.QuandlTicker;

public class QuandlAdapter {

    private final static Logger log = LoggerFactory.getLogger(JsonAdapter.class);

    public QuandlPresentationObject toPresentationObject(QuandlTicker quandlTicker) {

        QuandlPresentationObject output = new QuandlPresentationObject();
        output.setTicker(quandlTicker.getCode());
        output.setFrequency(quandlTicker.getFrequency());
        output.setDailyStockData(quandlTicker.getData());

        log.debug("About to return an adapted Quandl Presentation Object:", output.toString());
        return output;
    }
}
