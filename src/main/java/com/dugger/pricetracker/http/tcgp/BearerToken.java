package com.dugger.pricetracker.http.tcgp;

import java.util.concurrent.TimeUnit;

public class BearerToken {

  private String token;
  private long expiryTime = -1L;

  public BearerToken() {}

  public boolean shouldRenew() {
    return expiryTime == -1L || expiryTime - System.currentTimeMillis() > TimeUnit.HOURS.toMillis(1);
  }

  public String getToken() { return token; }

  public void setToken(String token) { this.token = token; }

  public void setExpiryTime(long expiryTime) { this.expiryTime = expiryTime; }
}
