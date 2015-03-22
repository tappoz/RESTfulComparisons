package org.tappoz.rest.remote.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.tappoz.rest.remote.data.QuandlTicker;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class JsonAdapterTest {

    JsonAdapter systemUnderTest;

    @Before
    public void init() throws IOException {
        systemUnderTest = new JsonAdapter();
    }

    @Test @Ignore
    public void getQuandlTickerTest() throws IOException {

        QuandlTicker outputObject = systemUnderTest.getSampleQuandlTicker();

        assertThat(outputObject.getCode(), is("AAPL"));

    }
}
