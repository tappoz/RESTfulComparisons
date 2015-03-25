package org.tappoz.rest.remote.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tappoz.rest.remote.data.QuandlDailyStockData;

import java.io.IOException;
import java.util.List;

public class QuandlDataFieldDeserializer extends JsonDeserializer<List<QuandlDailyStockData>> {

    private final static Logger log = LoggerFactory.getLogger(QuandlDataFieldDeserializer.class);

    @Override
    public List<QuandlDailyStockData> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        List<QuandlDailyStockData> quandlDailyStockDataList = Lists.newArrayList();
        if (p.getCurrentToken() == JsonToken.START_ARRAY) {

            p.nextToken(); // it should be the beginning of the first inner array
            boolean goOn = true;
            while(goOn) {
                if (p.getCurrentToken() == JsonToken.START_ARRAY) {

                    QuandlDailyStockData quandlDailyStockData = new QuandlDailyStockData();
                    p.nextToken();
                    String dayDate = p.getText();
                    p.nextToken();
                    String closingPrice = p.getText();
                    quandlDailyStockData.setDate(dayDate);
                    quandlDailyStockData.setClosingPrice(NumberUtils.createDouble(closingPrice));

                    quandlDailyStockDataList.add(quandlDailyStockData);

                    p.nextToken();

                    // if there's an end array, then the current object has completely been parsed!
                    if (p.getCurrentToken() != JsonToken.END_ARRAY) {
                        throw new IllegalStateException("We were expecting the end of the array representing an object!");
                    }
                } else {
                    throw new IllegalStateException("Here there should have been the beginning of the array representing the object!");
                }

                p.nextToken();
                // if there's another closing square bracket, then the array of arrays is at the end!
                if (p.getCurrentToken() == JsonToken.END_ARRAY) {
                    goOn = false;
                } else {
                    log.debug("Another item needs to be parsed!");
                }
            }
        } else {
            throw new IllegalStateException("There's something wrong while parsing the array of arrays representing objects...");
        }

        log.debug("About to return a list of size {}", quandlDailyStockDataList.size());
        return quandlDailyStockDataList;
    }
}
