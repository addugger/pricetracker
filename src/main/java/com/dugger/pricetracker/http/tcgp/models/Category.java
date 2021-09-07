package com.dugger.pricetracker.http.tcgp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.sql.Timestamp;

/**
 * Class for auto mapping result of TCGP's category detail method to POJO
 */
public class Category extends TcgGetJsonPojo<Category.CategoryResult> {

  public CategoryResult getResult() {
    return results.size() == 1 ? results.get(0) : null;
  }

  @Getter
  public static class CategoryResult implements TcgResult {
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
