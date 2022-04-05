package com.zooplus.crypto.controller;

import com.zooplus.crypto.model.Currency;
import com.zooplus.crypto.service.CryptoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(CryptoController.class)
class CryptoControllerTest {

    @MockBean
    private CryptoService cryptoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllCurrencies() throws Exception{
        when(cryptoService.cryptoCurrencyList()).thenReturn(getCryptoCurrencyList());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/crypto/list").contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    private List<Currency> getCryptoCurrencyList() {
        return Arrays.asList(
                new Currency("ABC","AB-Chain","AB-Chain (ABC)"),
                new Currency("ABC1","AB-Chain1","AB-Chain (ABC)1")
        );
    }
}

