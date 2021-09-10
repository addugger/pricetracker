package com.dugger.pricetracker.http.tcgp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.sql.Timestamp;

public class Groups extends TcgGetJsonPojo<Groups.Group> {

    @Getter
    public static class Group implements TcgResult {
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
