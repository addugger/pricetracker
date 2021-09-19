package com.dugger.pricetracker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Profile("dev")
public class DevConfig {

  @Bean
  public Map<String, Integer> tcgpRarities() {
    Map<String, Integer> stubRarirites = new HashMap<>();
    stubRarirites.put("U", 3);
    return stubRarirites;
  }
}
