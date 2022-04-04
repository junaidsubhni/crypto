package com.zooplus.crypto.service;

import com.zooplus.crypto.model.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class CryptoServiceTest {

    private CryptoService cryptoService;

    @BeforeEach
    void setup(){
        this.cryptoService = new CryptoService();
    }

    @Test
    void testCryptoCurrencyList(){

        List<Currency> actualResponse =  cryptoService.cryptoCurrencyList();
        assertThat(actualResponse.size()).isEqualTo(1);

    }

}
