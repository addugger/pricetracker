package com.dugger.pricetracker.mappers.tcgp;

import com.dugger.pricetracker.http.tcgp.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface CategoryMapper extends Converter<Category.CategoryResult, com.dugger.pricetracker.data.tcgp.models.category.Category> {


  @Mapping(source = "categoryId", target = "id")
  @Mapping(source = "modifiedOn", target = "modified_on")
  @Mapping(source = "displayName", target = "display_name")
  @Mapping(source = "sealedLabel", target = "sealed_label")
  @Mapping(source = "nonSealedLabel", target = "non_sealed_label")
  @Mapping(target = "doe", ignore = true)
  @Mapping(target = "dlu", ignore = true)
  com.dugger.pricetracker.data.tcgp.models.category.Category convert(Category.CategoryResult category);
}
