package com.dugger.pricetracker.http.tcgp;

import com.dugger.pricetracker.http.tcgp.models.Authenticate;

import java.util.concurrent.TimeUnit;

public class BearerToken {

  private static String token;
  private static long expiryTime = -1L;

  public BearerToken() {}

  public boolean shouldRenew() {
    return expiryTime == -1L || expiryTime - System.currentTimeMillis() > TimeUnit.HOURS.toMillis(1);
  }

  public String getToken() { return token; }

  public void setToken(String token) { this.token = token; }

  public long getExpiryTime() { return expiryTime; }

  public void setExpiryTime(long expiryTime) { this.expiryTime = expiryTime; }
}
