package com.dugger.pricetracker.http.tcgp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Languages extends TcgGetJsonPojo<Languages.Language> {

  protected static class Language implements TcgResult {
    @JsonProperty
    int languageId;
    @JsonProperty
    String name;
    @JsonProperty("abbr")
    String abbreviation;
  }
}
