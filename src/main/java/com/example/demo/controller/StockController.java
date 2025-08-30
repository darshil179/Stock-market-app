package com.example.demo.controller;

import com.example.demo.model.GlobalQuoteResponse;
import com.example.demo.service.StockService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class StockController {

    private final StockService stockService;
    private final ObjectMapper objectMapper;
    // Spring automatically provides the StockService object here
    @Autowired 
    public StockController(StockService stockService, ObjectMapper objectMapper) {
        this.stockService = stockService;
        this.objectMapper = objectMapper;
    }


    @GetMapping({"/", "/stocks"})
    public String showStocksAndSearch(Model model, @RequestParam(required = false) String symbol) {
        try {
            // Fetch and parse the list of top gainers
            String gainerLoserJson = stockService.getTopGainersAndLosers();
            JsonNode root = objectMapper.readTree(gainerLoserJson);
            model.addAttribute("topGainers", root.get("top_gainers"));

            // If a search symbol is provided, fetch and add the search result
            if (symbol != null && !symbol.isEmpty()) {
                GlobalQuoteResponse response = stockService.getStockQuote(symbol);
                model.addAttribute("stock", response.getGlobalQuote());
            }

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error fetching stock data. Please try again later.");
        }

        return "stocks";
    }

    // @GetMapping("/stocks/{symbol}")
    // public String getStockDetails(@PathVariable String symbol) {
    //     return stockService.getStockDetails(symbol);
    // }
}