package com.dugger.pricetracker.http.tcgp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Conditions extends TcgGetJsonPojo<Conditions.Condition> {

  protected static class Condition implements TcgResult {
    @JsonProperty
    int conditionId;
    @JsonProperty
    String name;
    @JsonProperty
    String abbreviation;
    @JsonProperty
    int displayOrder;
  }
}
