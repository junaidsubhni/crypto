package com.zooplus.crypto.controller;

import com.zooplus.crypto.model.Currency;
import com.zooplus.crypto.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    CryptoService cryptoService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Currency> currencies = cryptoService.cryptoCurrencyList();
        model.addAttribute("currencies", currencies);
        return "index";
    }
}
