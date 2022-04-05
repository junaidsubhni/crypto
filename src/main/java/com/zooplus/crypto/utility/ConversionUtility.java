package com.zooplus.crypto.utility;

import com.zooplus.crypto.model.ConversionResponse;
import java.util.Currency;

public class ConversionUtility {
    public static ConversionResponse currencyConversion(Double rate, String currencyCode){
        Currency currency = Currency.getInstance(currencyCode);
        ConversionResponse conversionResponse = new ConversionResponse();
        conversionResponse.setConversionRate(currency.getSymbol() +" "+ String.format("%.2f",rate));
        return conversionResponse;
    }
}
