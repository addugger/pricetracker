package com.dugger.pricetracker.http.tcgp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;

/**
 * Class for auto mapping result of TCGP's category detail method to POJO
 */
public class CategoryDetails {
  @JsonProperty
  boolean success;
  @JsonProperty
  List<String> errors;
  @JsonProperty
  List<CategoryDetailResult> results;


  private static class CategoryDetailResult {
    @JsonProperty
    int categoryId;
    @JsonProperty
    String name;
    @JsonProperty
    Timestamp modifiedOn;
    @JsonProperty
    String displayName;
    @JsonProperty
    String seoCategoryName;
    @JsonProperty
    String sealedLabel;
    @JsonProperty
    String nonSealedLabel;
    @JsonProperty
    String conditionGuideUrl;
    @JsonProperty
    boolean isScannable;
    @JsonProperty
    int popularity;
    @JsonProperty
    boolean isDirect;
  }
}
