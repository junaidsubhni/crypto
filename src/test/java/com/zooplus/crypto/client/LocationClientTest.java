package com.zooplus.crypto.client;

import com.zooplus.crypto.exception.LocationApiException;
import com.zooplus.crypto.model.IPLocation;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LocationClientTest {
    public static MockWebServer mockWebServer;

    private LocationClient locationClient;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void initialize(){
        this.locationClient = new LocationClient();
        locationClient.setIpLocationApiUrl(String.format("http://localhost:%s", mockWebServer.getPort()));
    }

    @Test
    void testIPLocationFetchCurrency(){

        String resp = "{\"status\":\"success\",\"currency\":\"USD\",\"countryCode\":\"US\"}";
        String ipAddress = "12.12.12.12";
        mockWebServer.enqueue(new MockResponse().
                setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(resp));

        IPLocation response = locationClient.fetchCurrencyByIP(ipAddress);

        assertThat(response.getStatus()).isEqualTo("success");
        assertThat(response.getCurrency()).isEqualTo("USD");
        assertThat(response.getCountryCode()).isEqualTo("US");
    }

    @Test
    void locationApiShouldReturnExceptionWhenStatusFail(){

        String resp = "{\"status\":\"fail\"}";
        String ipAddress = "12.12.12.12";
        mockWebServer.enqueue(new MockResponse().
                setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(resp));

        assertThrows(LocationApiException.class,() -> locationClient.fetchCurrencyByIP(ipAddress));
    }

}
