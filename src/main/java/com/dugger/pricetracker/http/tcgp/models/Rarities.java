package com.dugger.pricetracker.http.tcgp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class Rarities extends TcgGetJsonPojo<Rarities.Rarity> {

  @Getter
  public static class Rarity implements TcgResult {
    @JsonProperty
    int rarityId;
    @JsonProperty
    String displayText;
    @JsonProperty
    String dbValue;
  }
}
