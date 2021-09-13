package com.dugger.pricetracker.http.tcgp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rarities extends TcgGetJsonPojo<Rarities.Rarity> {

  public static class Rarity implements TcgResult {
    @JsonProperty
    int rarityId;
    @JsonProperty
    String displayText;
    @JsonProperty
    String dbValue;
  }
}
