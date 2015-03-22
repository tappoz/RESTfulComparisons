package org.tappoz.rest.remote.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tappoz.rest.remote.data.QuandlTicker;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class JsonAdapter {

    private final static Logger log = LoggerFactory.getLogger(JsonAdapter.class);

    ObjectMapper objectMapper;
    final QuandlTicker sampleQuandlTicker;

    public JsonAdapter() throws IOException {
        this.objectMapper =  new ObjectMapper();

        //File file = new File(getClass().getClassLoader().getResource("sampleQuandlTicker.json").getFile());
        InputStream is = getClass().getClassLoader().getResourceAsStream("sampleQuandlTicker.json");
        //sampleQuandlTicker = objectMapper.readValue(file, QuandlTicker.class);
        sampleQuandlTicker = objectMapper.readValue(is, QuandlTicker.class);
        log.info("Just parsed the Sample Quandl Ticker: {}", sampleQuandlTicker.toString());
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
