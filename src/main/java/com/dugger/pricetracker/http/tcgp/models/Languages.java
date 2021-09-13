package com.dugger.pricetracker.http.tcgp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class Languages extends TcgGetJsonPojo<Languages.Language> {

  @Getter
  public static class Language implements TcgResult {
    @JsonProperty
    int languageId;
    @JsonProperty
    String name;
    @JsonProperty("abbr")
    String abbreviation;
  }
}
