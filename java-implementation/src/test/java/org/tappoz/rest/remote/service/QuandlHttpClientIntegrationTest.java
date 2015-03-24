package org.tappoz.rest.remote.service;

import dagger.ObjectGraph;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.tappoz.rest.remote.api.ApiConfiguration;
import org.tappoz.rest.remote.api.ApiDiModule;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class QuandlHttpClientIntegrationTest {

    ObjectGraph objectGraph = ObjectGraph.create(new ApiDiModule(new ApiConfiguration()));

    QuandlHttpClient systemUnderTest = objectGraph.get(QuandlHttpClient.class);
    JsonValidator jsonValidator = objectGraph.get(JsonValidator.class);

    @Test
    public void getRemoteTickerTest() {

        String outputJson = systemUnderTest.getRemoteTicker("AAPL");

        assertThat(outputJson, is(notNullValue()));
        assertThat(outputJson, containsString("AAPL"));
        assertThat(outputJson, containsString("{"));
        assertThat(outputJson, containsString("}"));
        assertThat(outputJson, containsString("column_names"));
        assertThat(outputJson, containsString("from_date"));
        assertThat(outputJson, containsString("to_date"));

        assertTrue(jsonValidator.isValidJSON(outputJson));
    }
}
