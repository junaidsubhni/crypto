package com.zooplus.crypto.service;

import com.zooplus.crypto.client.CryptoClient;
import com.zooplus.crypto.client.LocationClient;
import com.zooplus.crypto.model.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CryptoServiceTest {

    private CryptoService cryptoService;

    @Mock
    private CryptoClient cryptoClient;

    @Mock
    private LocationService locationService;

    @BeforeEach
    void setup(){
        this.cryptoService = new CryptoService(cryptoClient,locationService);
    }

    @Test
    void testCryptoCurrencyList(){
        List<Currency> coinIOAssetsDtoList = Arrays.asList(
                new Currency("ABC","AB-Chain","AB-Chain (ABC)"),
                new Currency("ABC1","AB-Chain1","AB-Chain (ABC)1")
        );
        given(cryptoService.cryptoCurrencyList()).willReturn(coinIOAssetsDtoList);
        List<Currency> actualResponse =  cryptoService.cryptoCurrencyList();
        assertThat(actualResponse.size()).isEqualTo(2);

    }

}
