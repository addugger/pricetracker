package com.dugger.pricetracker;

import com.dugger.pricetracker.data.tcgp.models.category.CategoryRepository;
import com.dugger.pricetracker.http.tcgp.TCGPlayer;
import com.dugger.pricetracker.http.tcgp.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PricetrackerApplication {

  private static final Logger logger = LoggerFactory.getLogger(PricetrackerApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(PricetrackerApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(CategoryRepository categoryRepository, TCGPlayer tcgPlayer) {
    return (args) -> {

      tcgPlayer.authenticate();
      logger.info("TCGP Bearer token: " + tcgPlayer.bearerToken.getToken());

      Category category = tcgPlayer.getCategoryDetails(1);

      Groups groups = tcgPlayer.getCategoryGroups(1, 10, 0);
      logger.info("Groups: " + groups);

      Products products = tcgPlayer.getGroupProducts(7, 10, 0);

      Languages languages = tcgPlayer.getLanguages(1);

      Conditions conditions = tcgPlayer.getConditions(1);

      Rarities rarities = tcgPlayer.getRarities(1);

      Printings printings = tcgPlayer.getPrintings(1);

      Category.CategoryResult catResult = category.getResult();

      com.dugger.pricetracker.data.tcgp.models.category.Category catEntity = new com.dugger.pricetracker.data.tcgp.models.category.Category();
      catEntity.setId(catResult.getCategoryId());
      catEntity.setName(catResult.getName());
      catEntity.setModified_on(catResult.getModifiedOn());
      catEntity.setDisplay_name(catResult.getDisplayName());
      catEntity.setSealed_label(catResult.getSealedLabel());
      catEntity.setNon_sealed_label(catResult.getNonSealedLabel());

      categoryRepository.save(catEntity);
      com.dugger.pricetracker.data.tcgp.models.category.Category catRetrieved = categoryRepository.findById(1).get();


      logger.info("pause");
//      logger.info("All rows");
//      logger.info("######################");
//      repository.findAll().spliterator().forEachRemaining(row -> logger.info(row.toString()));
//      logger.info("");
//
//      logger.info("Rows with last name Dugger");
//      logger.info("#######################");
//      repository.findByLastName("Dugger").forEach(row -> logger.info(row.toString()));
    };
  }

}
