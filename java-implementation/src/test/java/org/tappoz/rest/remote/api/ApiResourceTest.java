package org.tappoz.rest.remote.api;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.tappoz.rest.remote.data.QuandlPresentationObject;
import org.tappoz.rest.remote.data.QuandlTicker;
import org.tappoz.rest.remote.service.JsonAdapter;
import org.tappoz.rest.remote.service.QuandlAdapter;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApiResourceTest {

    @Mock
    JsonAdapter jsonAdapter;
    @Mock
    QuandlAdapter quandlAdapter;
    @InjectMocks
    ApiResource systemUnderTest;

    @Test @Ignore
    public void getTickerFromPathParameterTest() throws IOException {

        QuandlTicker mockedQuandlTicker = mock(QuandlTicker.class);
        QuandlPresentationObject mockedQuandlPresentationObject = mock(QuandlPresentationObject.class);

        when(jsonAdapter.getSampleQuandlTicker()).thenReturn(mockedQuandlTicker);
        when(quandlAdapter.toPresentationObject(mockedQuandlTicker)).thenReturn(mockedQuandlPresentationObject);

        // TODO deal with the remote retrieval
        QuandlPresentationObject output = systemUnderTest.getTickerFromPathParameter("WHATEVER");

        assertThat(output, is(mockedQuandlPresentationObject));
    }
}
