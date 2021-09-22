package com.dugger.pricetracker.http.tcgp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Class for auto mapping result of TCGP's category detail method to POJO
 */
public class Category extends TcgGetJsonPojo<Category.CategoryResult> {

  public CategoryResult getResult() {
    return results.size() == 1 ? results.get(0) : null;
  }

  @Getter
  @Setter
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

    public CategoryResult(
        int categoryId, String name, Timestamp modifiedOn, String displayName, String seoCategoryName, String sealedLabel,
        String nonSealedLabel, String conditionGuideUrl, boolean isScannable, int popularity, boolean isDirect
    ) {
      this.categoryId = categoryId;
      this.name = name;
      this.modifiedOn = modifiedOn;
      this.displayName = displayName;
      this.seoCategoryName = seoCategoryName;
      this.sealedLabel = sealedLabel;
      this.nonSealedLabel = nonSealedLabel;
      this.conditionGuideUrl = conditionGuideUrl;
      this.isScannable = isScannable;
      this.popularity = popularity;
      this.isDirect = isDirect;
    }
  }
}
