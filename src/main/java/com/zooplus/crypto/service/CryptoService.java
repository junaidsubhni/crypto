package com.zooplus.crypto.service;

import com.zooplus.crypto.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CryptoService {

    @Autowired
    public CryptoService() {

    }

    public List<Currency> cryptoCurrencyList() {
        List<Currency> currencyList = new ArrayList<>();
        Currency currency = new Currency();
        currency.setId("123");
        currency.setName("Dirham");
        currencyList.add(currency);
        return currencyList;
    }
}
