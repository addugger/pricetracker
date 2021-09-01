package com.dugger.pricetracker.http.tcgp.models;

import com.dugger.pricetracker.http.models.JsonPojo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;

public class Groups implements JsonPojo {
    @JsonProperty
    int totalItems;
    @JsonProperty
    boolean success;
    @JsonProperty
    List<String> errors;
    @JsonProperty
    List<Group> results;

    private static class Group {
        @JsonProperty
        int groupId;
        @JsonProperty
        String name;
        @JsonProperty
        String abbreviation;
        @JsonProperty
        boolean isSupplemental;
        @JsonProperty
        Timestamp publishedOn;
        @JsonProperty
        Timestamp modifiedOn;
        @JsonProperty
        int categoryId;
    }
}
