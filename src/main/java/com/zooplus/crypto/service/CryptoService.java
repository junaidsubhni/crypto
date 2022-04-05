package com.zooplus.crypto.service;

import com.zooplus.crypto.client.CryptoClient;
import com.zooplus.crypto.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoService {

    private final CryptoClient cryptoClient;

    @Autowired
    public CryptoService(CryptoClient cryptoClient) {
        this.cryptoClient = cryptoClient;
    }

    public List<Currency> cryptoCurrencyList() {
        return cryptoClient.fetchCurrencyList();
    }
}
