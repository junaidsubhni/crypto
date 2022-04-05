package com.zooplus.crypto.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.zooplus.crypto.exception.CoinApiException;
import com.zooplus.crypto.model.ConversionRate;
import com.zooplus.crypto.model.Currencies;
import com.zooplus.crypto.model.Currency;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CryptoClient {

    private final OkHttpClient client;

    private static final Logger log = LoggerFactory.getLogger(CryptoClient.class);

    @Value("${crypto.currency.API_URL}")
    private String currencyListUrl;

    @Value("${crypto.currency.API_KEY}")
    private String currencyAPIKey;

    @Autowired
    public CryptoClient() {
        this.client = new OkHttpClient();
    }

    public List<Currency> fetchCurrencyList() {

        URL url = new HttpUrl.Builder()
                .host(HttpUrl.parse(currencyListUrl).host())
                .scheme(HttpUrl.parse(currencyListUrl).scheme())
                .port(HttpUrl.parse(currencyListUrl).port())
                .addQueryParameter("access_key", currencyAPIKey)
                .addPathSegment("api")
                .addPathSegment("list")
                .build().url();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String responseBody = Objects.requireNonNull(response.body()).string();
            Currencies currenciesResponse = new ObjectMapper().readValue(responseBody, new TypeReference<>(){
            });
            return new ArrayList<>(currenciesResponse.getCrypto().values());
        } catch (IOException e) {
            log.error("Error in fetching Currencies", e);
            throw new CoinApiException("Error in fetching Currencies");
        }
    }

    public Double fetchCryptoConversionRates(String currency, String targetCurrencyCode) {

        URL url = new HttpUrl.Builder()
                .host(HttpUrl.parse(currencyListUrl).host())
                .scheme(HttpUrl.parse(currencyListUrl).scheme())
                .port(HttpUrl.parse(currencyListUrl).port())
                .addQueryParameter("access_key", currencyAPIKey)
                .addQueryParameter("target", targetCurrencyCode)
                .addQueryParameter("symbols", currency)
                .addPathSegment("api")
                .addPathSegment("live")
                .build().url();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String responseBody = Objects.requireNonNull(response.body()).string();
            ConversionRate conversionRate = new ObjectMapper().readValue(responseBody, new TypeReference<>(){
            });
            return conversionRate.getRates().get(currency);
        } catch (IOException e) {
            log.error("Error in fetching Conversion  Rates", e);
            throw new CoinApiException("Error in fetching Conversion  Rates");
        }
    }
}
