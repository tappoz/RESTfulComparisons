package org.tappoz.rest.remote.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.tappoz.rest.remote.data.QuandlPresentationObject;
import org.tappoz.rest.remote.data.QuandlTicker;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class QuandlAdapterTest {

    QuandlAdapter systemUnderTest = new QuandlAdapter();

    @Test
    public void toPresentationObjectTest() {

        QuandlTicker input = new QuandlTicker();
        input.setCode("foo");
        input.setFrequency("bar");

        QuandlPresentationObject output = systemUnderTest.toPresentationObject(input);

        assertThat(output.getTicker(), is("foo"));
        assertThat(output.getFrequency(), is("bar"));
    }
}
