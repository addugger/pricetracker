package com.dugger.pricetracker;

import com.dugger.pricetracker.data.models.demotable.DemoTableRepository;
import com.dugger.pricetracker.http.tcgp.TCGPlayer;
import com.dugger.pricetracker.http.tcgp.models.Groups;
import com.dugger.pricetracker.http.tcgp.models.Products;
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
  public CommandLineRunner demo(DemoTableRepository repository, TCGPlayer tcgPlayer) {
    return (args) -> {

      tcgPlayer.authenticate();
      logger.info("TCGP Bearer token: " + tcgPlayer.bearerToken.getToken());

      logger.info("Cat 1 details: \n" + tcgPlayer.getCategoryDetails(1));

      Groups groups = tcgPlayer.getCategoryGroups(1, 10, 0);
      logger.info("Groups: " + groups);

      Products products = tcgPlayer.getGroupProducts(7, 10, 0);
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
