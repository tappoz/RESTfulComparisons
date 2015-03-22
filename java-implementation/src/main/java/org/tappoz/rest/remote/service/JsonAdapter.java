package org.tappoz.rest.remote.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tappoz.rest.remote.data.QuandlTicker;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;

public class JsonAdapter {

    private final static Logger log = LoggerFactory.getLogger(JsonAdapter.class);

    ObjectMapper objectMapper;
    QuandlTicker sampleQuandlTicker;

    @Inject
    public JsonAdapter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

        InputStream is = getClass().getClassLoader().getResourceAsStream("sampleQuandlTicker.json");
        try {
            sampleQuandlTicker = objectMapper.readValue(is, QuandlTicker.class);
            log.info("Just parsed the Sample Quandl Ticker: {}", sampleQuandlTicker.toString());
        } catch (IOException e) {
            log.error("It was not possible to fetch the file containing the JSON object, the {} said: {}", e.getClass().getCanonicalName(), e.getMessage());
        }
    }

    public QuandlTicker getSampleQuandlTicker() {

        log.debug("About to return the Sample Quandl Ticker information:", sampleQuandlTicker.toString());
        return this.sampleQuandlTicker;
    }

    public QuandlTicker parseQuandlTicker(String jsonObject) throws IOException {

        QuandlTicker parsedObject = objectMapper.readValue(jsonObject, QuandlTicker.class);
        log.debug("About to return the a Quandl Ticker object:", parsedObject.toString());
        return parsedObject;
    }
}
