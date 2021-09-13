package com.dugger.pricetracker.http.tcgp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class Conditions extends TcgGetJsonPojo<Conditions.Condition> {

  @Getter
  public static class Condition implements TcgResult {
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
