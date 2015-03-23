package org.tappoz.rest.remote.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class QuandlHttpClientIntegrationTest {

    QuandlHttpClient systemUnderTest = new QuandlHttpClient();

    @Test // @Ignore
    public void getRemoteTickerTest() {

        String outputJson = systemUnderTest.getRemoteTicker("AAPL");

        assertThat(outputJson, is(notNullValue()));
        assertThat(outputJson, containsString("AAPL"));
        assertThat(outputJson, containsString("{"));
        assertThat(outputJson, containsString("}"));
        assertThat(outputJson, containsString("column_names"));
        assertThat(outputJson, containsString("from_date"));
        assertThat(outputJson, containsString("to_date"));
    }
}
