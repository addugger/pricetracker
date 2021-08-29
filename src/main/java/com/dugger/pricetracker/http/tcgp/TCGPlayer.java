package com.dugger.pricetracker.http.tcgp;

import com.dugger.pricetracker.http.Get;
import com.dugger.pricetracker.http.Post;
import com.dugger.pricetracker.http.tcgp.models.Authenticate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TCGPlayer {

  private Logger logger = LoggerFactory.getLogger(getClass());

  public BearerToken bearerToken;

  private static final String urlBase = "api.tcgplayer.com/v1.39.0";
  private Map<String, String> headers;

  private ObjectMapper mapper = new ObjectMapper();

  public TCGPlayer() {
    bearerToken = new BearerToken();
    headers = new HashMap<>();
//    headers.put("Authorization", "Bearer " + bearerToken.getToken());
    headers.put("Accept", "application/json");
  }

  /* returns true if success or nothing was needed. false if exception thrown */
  public boolean authenticate() {
    if (bearerToken.shouldRenew()) {
      try {
        Post post = getBasePost();
        // this is the only TCGP endpoint that doesn't include the version in the path
        post.setUrlBase("api.tcgplayer.com");
        post.setEndpoint("/token");
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credentials");
        params.put("client_id", "XXXXXXXXXXXXXXX");
        params.put("client_secret", "XXXXXXXXXXX");
        post.setParams(params);
        String authResponse = post.sendRequest();
        Authenticate auth = mapper.readValue(authResponse, Authenticate.class);
        bearerToken.setToken(auth.getAccessToken());
        bearerToken.setExpiryTime(auth.getExpires().getTime());
        return true;
      } catch (JsonProcessingException e) {
        logger.error("There was an issue parsing the response from the authenticate method", e);
        return false;
      }
    }
    else return true;
  }

  public Get getBaseGet() { return new Get(null, headers, urlBase, null, false); }

  public Post getBasePost() { return  new Post(null, headers, urlBase, null, true); }
}
