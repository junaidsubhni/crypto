package com.zooplus.crypto.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    protected ResponseEntity<Object> handleInvalidDataException(
            RuntimeException ex, WebRequest request) {
        String responseBody = "Ooops! Something went wrong";
        ErrorModel errorModel = ErrorModel.builder()
                .message(responseBody)
                .code("GEN_EX")
                .build();
        return handleExceptionInternal(ex, errorModel, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {LocationApiException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleLocationApiException(
            RuntimeException ex, WebRequest request) {

        LocationApiException locationApiException = (LocationApiException) ex;

        ErrorModel errorModel = ErrorModel.builder()
                .message(locationApiException.getMessage())
                .code("LOCATION_EX")
                .build();
        return handleExceptionInternal(ex, errorModel, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {CoinApiException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleCoinApiException(
            RuntimeException ex, WebRequest request) {

        CoinApiException coinApiException = (CoinApiException) ex;

        ErrorModel errorModel = ErrorModel.builder()
                .message(coinApiException.getMessage())
                .code("CURRENCY_EX")
                .build();
        return handleExceptionInternal(ex, errorModel, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
