package com.dugger.pricetracker.http.tcgp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

/**
 * Class for auto mapping result of TCGP's authenticate method to POJO
 */
public class Authenticate {
  @JsonProperty("access_token")
  String accessToken;
  @JsonProperty("token_type")
  String token_type;
  @JsonProperty("expires_in")
  String expires_in;
  @JsonProperty("userName")
  String userName;
  @JsonProperty(".issued")
  Timestamp issued;
  @JsonProperty(".expires")
  Timestamp expires;

  public String getAccessToken() { return accessToken; }

  public Timestamp getExpires() { return expires; }

}
