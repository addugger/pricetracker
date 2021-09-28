package com.dugger.pricetracker.mappers;

import com.dugger.pricetracker.http.tcgp.models.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

class TCGPMapperTest {

  @Test
  void convertsCorrectly() {
    Timestamp modifiedOn = new Timestamp(System.currentTimeMillis());
    Category.CategoryResult jsonCat = new Category.CategoryResult(
        1, "name", modifiedOn, "displayName", "seoCategoryName",
        "sealedLabel", "nonSealedLabel", "conditionGuideUrl", true, 1, false
    );
    com.dugger.pricetracker.data.tcgp.models.category.Category entityCat = new TCGPMapperImpl().jsonToEntity(jsonCat);
    Assertions.assertNotNull(entityCat);
    Assertions.assertEquals(1, entityCat.getId());
    Assertions.assertEquals("name", entityCat.getName());
    Assertions.assertEquals(modifiedOn, entityCat.getModified_on());
    Assertions.assertEquals("displayName", entityCat.getDisplay_name());
    Assertions.assertEquals("sealedLabel", entityCat.getSealed_label());
    Assertions.assertEquals("nonSealedLabel", entityCat.getNon_sealed_label());
  }
}