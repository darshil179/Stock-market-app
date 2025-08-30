package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalQuoteResponse {
    @JsonProperty("Global Quote")
    private GlobalQuote globalQuote;
}