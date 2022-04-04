package com.zooplus.crypto.controller;

import com.zooplus.crypto.model.Currency;
import com.zooplus.crypto.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("crypto")
public class CryptoController {

    private final CryptoService cryptoService;

    @Autowired
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping(value = "/list")
    public List<Currency> listCryptoCurrencies() {
        return cryptoService.cryptoCurrencyList();
    }

}
