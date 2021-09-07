package com.dugger.pricetracker;

import com.dugger.pricetracker.http.tcgp.TCGPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Configuration
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
}
