package com.dugger.pricetracker.http.tcgp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.sql.Timestamp;

public class Printings extends TcgGetJsonPojo<Printings.Printing> {

  @Getter
  public static class Printing implements TcgResult {
    @JsonProperty
    int printingId;
    @JsonProperty
    String name;
    @JsonProperty
    int displayOrder;
    @JsonProperty
    Timestamp modifiedOn;
  }
}
