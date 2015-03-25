package org.tappoz.rest.remote.data;

import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class QuandlPresentationObject {

    private String ticker;
    private String frequency;
    private List<QuandlDailyStockData> dailyStockData;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public List<QuandlDailyStockData> getDailyStockData() {
        return dailyStockData;
    }

    public void setDailyStockData(final List<QuandlDailyStockData> dailyStockData) {
        this.dailyStockData = dailyStockData;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(obj, this);
    }
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
