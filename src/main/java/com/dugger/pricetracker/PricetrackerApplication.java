package com.dugger.pricetracker;

import com.dugger.pricetracker.data.tcgp.models.category.CategoryRepository;
import com.dugger.pricetracker.data.tcgp.models.condition.Condition;
import com.dugger.pricetracker.data.tcgp.models.condition.ConditionRepository;
import com.dugger.pricetracker.data.tcgp.models.group.Group;
import com.dugger.pricetracker.data.tcgp.models.group.GroupRepository;
import com.dugger.pricetracker.data.tcgp.models.language.Language;
import com.dugger.pricetracker.data.tcgp.models.language.LanguageRepository;
import com.dugger.pricetracker.data.tcgp.models.printing.Printing;
import com.dugger.pricetracker.data.tcgp.models.printing.PrintingRepository;
import com.dugger.pricetracker.data.tcgp.models.product.Product;
import com.dugger.pricetracker.data.tcgp.models.product.ProductRepository;
import com.dugger.pricetracker.data.tcgp.models.rarity.Rarity;
import com.dugger.pricetracker.data.tcgp.models.rarity.RarityRepository;
import com.dugger.pricetracker.data.tcgp.models.sku.Sku;
import com.dugger.pricetracker.data.tcgp.models.sku.SkuRepository;
import com.dugger.pricetracker.http.tcgp.TCGPlayer;
import com.dugger.pricetracker.http.tcgp.models.*;
import com.dugger.pricetracker.mappers.TCGPMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class PricetrackerApplication {

  private static final Logger logger = LoggerFactory.getLogger(PricetrackerApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(PricetrackerApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(TCGPlayer tcgPlayer,
                                Map<String, Integer> tcgpRarities,
                                CategoryRepository categoryRepository,
                                GroupRepository groupRepository,
                                ProductRepository productRepository,
                                SkuRepository skuRepository,
                                ConditionRepository conditionRepository,
                                LanguageRepository languageRepository,
                                PrintingRepository printingRepository,
                                RarityRepository rarityRepository,
                                TCGPMapper mapper) {
    return (args) -> {

      tcgPlayer.authenticate();
      logger.info("TCGP Bearer token: " + tcgPlayer.bearerToken.getToken());

      Category category = tcgPlayer.getCategoryDetails(1);
      Category.CategoryResult catResult = category.getResult();

      Groups groups = tcgPlayer.getCategoryGroups(catResult.getCategoryId(), 10, 0);
      Groups.Group group = groups.getResults().get(0);

      Group mappedGroup = mapper.jsonToEntity(group);

      Products products = tcgPlayer.getGroupProducts(group.getGroupId(), 10, 0);
      Products.Product product = products.getResults().get(0);
      Products.Sku sku = product.getSkus().get(0);

      Languages languages = tcgPlayer.getLanguages(catResult.getCategoryId());
      Languages.Language language = languages.getResults().get(0);

      Conditions conditions = tcgPlayer.getConditions(catResult.getCategoryId());
      Conditions.Condition condition = conditions.getResults().get(0);

      Rarities rarities = tcgPlayer.getRarities(catResult.getCategoryId());
      Rarities.Rarity rarity = rarities.getResults().get(0);

      Printings printings = tcgPlayer.getPrintings(catResult.getCategoryId());
      Printings.Printing printing = printings.getResults().get(0);


      com.dugger.pricetracker.data.tcgp.models.category.Category catEntity = new com.dugger.pricetracker.data.tcgp.models.category.Category();
      catEntity.setId(catResult.getCategoryId());
      catEntity.setName(catResult.getName());
      catEntity.setModified_on(catResult.getModifiedOn());
      catEntity.setDisplay_name(catResult.getDisplayName());
      catEntity.setSealed_label(catResult.getSealedLabel());
      catEntity.setNon_sealed_label(catResult.getNonSealedLabel());

      categoryRepository.save(catEntity);
      com.dugger.pricetracker.data.tcgp.models.category.Category catRetrieved = categoryRepository.findById(1).get();

      com.dugger.pricetracker.data.tcgp.models.category.Category catRef = categoryRepository.getById(group.getCategoryId());

      Group groupEntity = new Group();
      groupEntity.setId(group.getGroupId());
      groupEntity.setCategory(catRef);
      groupEntity.setName(group.getName());
      groupEntity.setAbbreviation(group.getAbbreviation());
      groupEntity.set_supplemental(group.isSupplemental());
      groupEntity.setPublished_on(group.getPublishedOn());
      groupEntity.setModified_on(group.getModifiedOn());
      groupRepository.save(groupEntity);

      Group groupRetrieved = groupRepository.findById(group.getGroupId()).get();

      Language languageEntity = new Language();
      languageEntity.setId(sku.getLanguageId());
      languageEntity.setCategory(catRef);
      languageEntity.setName(language.getName());
      languageEntity.setAbbreviation(language.getAbbreviation());
      languageRepository.save(languageEntity);

      Condition conditionEntity = new Condition();
      conditionEntity.setId(sku.getConditionId());
      conditionEntity.setCategory(catRef);
      conditionEntity.setName(condition.getName());
      conditionEntity.setAbbreviation(condition.getAbbreviation());
      conditionRepository.save(conditionEntity);

      Rarity rarityEntity = new Rarity();
      rarityEntity.setId(
          tcgpRarities.getOrDefault(
              product.getExtendedValue("Rarity").orElse("UnknownRarity"),
              Rarity.DEFAULT_ID
          )
      );
      rarityEntity.setCategory(catRef);
      rarityEntity.setName(rarity.getDisplayText());
      rarityEntity.setAbbreviation(rarity.getDbValue());
      rarityRepository.save(rarityEntity);

      Printing printingEntity = new Printing();
      printingEntity.setId(sku.getPrintingId());
      printingEntity.setCategory(catRef);
      printingEntity.setName(printing.getName());
      printingEntity.setModified_on(printing.getModifiedOn());
      printingRepository.save(printingEntity);

      Product productEntity = new Product();
      productEntity.setId(product.getProductId());
      productEntity.setGroup(groupRepository.getById(group.getGroupId()));
      productEntity.setCategory(catRef);
      productEntity.setRarity(rarityRepository.getById(-1));
      productEntity.setName(product.getName());
      productEntity.setClean_name(product.getCleanName());
      productEntity.setImage_url(product.getImageUrl());
      productEntity.setModified_on(product.getModifiedOn());
      productRepository.save(productEntity);

      Sku skuEntity = new Sku();
      skuEntity.setId(sku.getSkuId());
      skuEntity.setProduct(productRepository.getById(sku.getProductId()));
      skuEntity.setLanguage(languageRepository.getById(sku.getLanguageId()));
      skuEntity.setPrinting(printingRepository.getById(sku.getPrintingId()));
      skuEntity.setCondition(conditionRepository.getById(sku.getConditionId()));
      skuRepository.save(skuEntity);



      logger.info("pause");
    };
  }

}
