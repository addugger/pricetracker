package com.dugger.pricetracker.http.tcgp.models;

import com.dugger.pricetracker.http.models.JsonPojo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class TcgGetJsonPojo<T extends TcgResult> implements JsonPojo {
    @JsonProperty
    int totalItems;
    @JsonProperty
    boolean success;
    @JsonProperty
    List<String> errors;
    @JsonProperty
    List<T> results;
}
