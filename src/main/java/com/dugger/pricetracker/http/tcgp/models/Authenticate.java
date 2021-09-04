package com.dugger.pricetracker.http.tcgp.models;

import com.dugger.pricetracker.http.models.JsonPojo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.sql.Timestamp;

/**
 * Class for auto mapping result of TCGP's authenticate method to POJO
 */
// TODO add errors field
public class Authenticate implements JsonPojo {
  @JsonProperty("access_token")
  @Getter
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
  @Getter
  Timestamp expires;
  @JsonProperty
  @Getter
  String error;
  @JsonProperty("error_description")
  @Getter
  String errorMessage;
}
