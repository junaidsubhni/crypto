package com.zooplus.crypto.service;

import com.zooplus.crypto.utility.ConversionUtility;
import com.zooplus.crypto.client.CryptoClient;
import com.zooplus.crypto.model.ConversionResponse;
import com.zooplus.crypto.model.Currency;
import com.zooplus.crypto.model.IPLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoService {

    private final CryptoClient cryptoClient;

    private final LocationService locationService;

    @Autowired
    public CryptoService(CryptoClient cryptoClient, LocationService locationService) {
        this.cryptoClient = cryptoClient;
        this.locationService = locationService;
    }

    public List<Currency> cryptoCurrencyList() {
        return cryptoClient.fetchCurrencyList();
    }

    public ConversionResponse convert(String ipAddress, String currency) {
        IPLocation location = locationService.fetchLocation(ipAddress);
        Double conversionRate = cryptoClient.fetchCryptoConversionRates(currency, location.getCurrency());

        return ConversionUtility.currencyConversion(conversionRate, location.getCurrency());
    }



}
