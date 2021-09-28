package com.dugger.pricetracker;

import com.dugger.pricetracker.data.tcgp.models.rarity.Rarity;
import com.dugger.pricetracker.data.tcgp.models.rarity.RarityRepository;
import com.dugger.pricetracker.http.tcgp.TCGPlayer;
import com.dugger.pricetracker.mappers.TCGPMapper;
import com.dugger.pricetracker.mappers.TCGPMapperImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Configuration
@EnableJpaAuditing
public class Config {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @PostConstruct
  public void init() {
    logger.info("Setting JVM timezone to UTC");
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  @Bean
  public Logger logger() { return LoggerFactory.getLogger(getClass()); }

  @Bean
  public TCGPlayer tcgPlayer() { return new TCGPlayer(); }

  @Bean
  public TCGPMapper groupMapper() { return new TCGPMapperImpl(); }

  @Bean
  @Profile("disabled")
  public Map<String, Integer> tcgpRarities(RarityRepository rarityRepository) {
    return rarityRepository.findAll().stream().collect(Collectors.toMap(Rarity::getAbbreviation, Rarity::getId));
  }
}
