package com.example.demo.service;

import com.example.demo.model.GlobalQuoteResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StockService {

    private final String API_KEY = "L7FCZVW9Q96UYY1N";
    private final String BASE_URL = "https://www.alphavantage.co/query";

    private final RestTemplate restTemplate = new RestTemplate();

    public GlobalQuoteResponse getStockQuote(String symbol) {
        String url = BASE_URL + "?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + API_KEY;
        return restTemplate.getForObject(url, GlobalQuoteResponse.class);
    }

    // This is the missing method that the controller is trying to call
    public String getStockDetails(String symbol) {
        String url = BASE_URL + "?function=OVERVIEW&symbol=" + symbol + "&apikey=" + API_KEY;
        return restTemplate.getForObject(url, String.class);
    }

    public String getTopGainersAndLosers() {
        String url = "https://www.alphavantage.co/query?function=TOP_GAINERS_LOSERS&apikey=" + API_KEY;
        return restTemplate.getForObject(url, String.class);
    }
}