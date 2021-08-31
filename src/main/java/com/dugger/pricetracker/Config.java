package com.dugger.pricetracker;

import com.dugger.pricetracker.http.tcgp.TCGPlayer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Bean
  public TCGPlayer tcgPlayer() { return new TCGPlayer(); }
}
