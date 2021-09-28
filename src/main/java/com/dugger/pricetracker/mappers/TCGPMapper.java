package com.dugger.pricetracker.mappers;

import com.dugger.pricetracker.data.tcgp.models.category.CategoryRepository;
import com.dugger.pricetracker.data.tcgp.models.group.Group;
import com.dugger.pricetracker.http.tcgp.models.Category;
import com.dugger.pricetracker.http.tcgp.models.Groups;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Mapper
@Service
public abstract class TCGPMapper {

  @Autowired
  CategoryRepository catRepo;

  @Named("idToCategory")
  public com.dugger.pricetracker.data.tcgp.models.category.Category idToCategory(int categoryId) {
    return catRepo.getById(categoryId);
  }

  @Mapping(source = "categoryId", target = "id")
  @Mapping(source = "modifiedOn", target = "modified_on")
  @Mapping(source = "displayName", target = "display_name")
  @Mapping(source = "sealedLabel", target = "sealed_label")
  @Mapping(source = "nonSealedLabel", target = "non_sealed_label")
  @Mapping(target = "doe", ignore = true)
  @Mapping(target = "dlu", ignore = true)
  public abstract com.dugger.pricetracker.data.tcgp.models.category.Category jsonToEntity(Category.CategoryResult category);

  @Mapping(source = "groupId", target = "id")
  @Mapping(source = "categoryId" , target = "category", qualifiedByName = "idToCategory")
  @Mapping(source = "supplemental", target = "_supplemental")
  @Mapping(source = "publishedOn", target = "published_on")
  @Mapping(source = "modifiedOn", target = "modified_on")
  @Mapping(target = "doe", ignore = true)
  @Mapping(target = "dlu", ignore = true)
  public abstract Group jsonToEntity(Groups.Group group);
}
