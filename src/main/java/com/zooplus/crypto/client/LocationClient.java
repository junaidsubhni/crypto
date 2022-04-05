package com.zooplus.crypto.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zooplus.crypto.exception.CoinApiException;
import com.zooplus.crypto.model.IPLocation;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@Service
public class LocationClient {
    private final OkHttpClient client;

    private static final Logger log = LoggerFactory.getLogger(LocationClient.class);

    @Value("${ip.location.API_URL}")
    private String ipLocationApiUrl;

    @Autowired
    public LocationClient() {
        this.client = new OkHttpClient();
    }

    public IPLocation fetchCurrencyByIP(String ipAddress) {

        URL url = new HttpUrl.Builder()
                .host(HttpUrl.parse(ipLocationApiUrl).host())
                .scheme(HttpUrl.parse(ipLocationApiUrl).scheme())
                .port(HttpUrl.parse(ipLocationApiUrl).port())
                .addQueryParameter("fields", "status,currency,countryCode")
                .addPathSegment("json")
                .addPathSegment(ipAddress)
                .build().url();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String responseBody = Objects.requireNonNull(response.body()).string();
            IPLocation iPLocation = new ObjectMapper().readValue(responseBody, new TypeReference<>(){
            });
            return iPLocation;
        } catch (IOException e) {
            log.error("Error in fetching IP Location", e);
            throw new CoinApiException("Error in fetching IP Location");
        }
    }
}
