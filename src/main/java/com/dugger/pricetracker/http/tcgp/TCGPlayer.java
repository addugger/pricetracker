package com.dugger.pricetracker.http.tcgp;

import com.dugger.pricetracker.http.Get;
import com.dugger.pricetracker.http.Post;
import com.dugger.pricetracker.http.Request;
import com.dugger.pricetracker.http.models.JsonPojo;
import com.dugger.pricetracker.http.tcgp.models.Authenticate;
import com.dugger.pricetracker.http.tcgp.models.Category;
import com.dugger.pricetracker.http.tcgp.models.Groups;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TCGPlayer {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  public BearerToken bearerToken;

  private static final String urlBase = "api.tcgplayer.com/v1.39.0";
  private final Map<String, String> headers;

  private final ObjectMapper mapper = new ObjectMapper();

  @Value("${tcgp.public.key}")
  private String clientId;
  @Value("${tcgp.private.key}")
  private String clientSecret;

  public TCGPlayer() {
    bearerToken = new BearerToken();
    headers = new HashMap<>();
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
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        post.setParams(params);
        String authResponse = post.sendRequest();
        Authenticate auth = mapper.readValue(authResponse, Authenticate.class);
        bearerToken.setToken(auth.getAccessToken());
        bearerToken.setExpiryTime(auth.getExpires().getTime());
        headers.put("Authorization", "Bearer " + bearerToken.getToken());
        return true;
      } catch (JsonProcessingException e) {
        logger.error("There was an issue parsing the response from the authenticate method", e);
        return false;
      }
    }
    else return true;
  }

  public Category getCategoryDetails(int categoryId) {
    Get get = getBaseGet();
    get.setParams(Request.emptyParams);
    get.setEndpoint("/catalog/categories/" + categoryId);
    return parseResponse(get.sendRequest(), Category.class);
  }

  public Groups getCategoryGroups(int categoryId, int limit, int offset) {
    Get get = getBaseGet();
    Map<String, String> params = new HashMap<>();
    params.put("limit", Integer.toString(limit));
    params.put("offset", Integer.toString(offset));
    get.setParams(params);
    get.setEndpoint("/catalog/categories/" + categoryId + "/groups");
    return parseResponse(get.sendRequest(), Groups.class);
  }

  public Get getBaseGet() { return new Get(null, headers, urlBase, null, true); }

  public Post getBasePost() { return  new Post(null, headers, urlBase, null, true); }

  private <T extends JsonPojo> T parseResponse(String jsonResponse, Class<T> pojoClass) {
    T pojo = null;
    try {
      pojo = mapper.readValue(jsonResponse, pojoClass);
    } catch (JsonProcessingException e) {
      logger.error("There was an issue parsing the response into class " + pojoClass.toString(), e);
    }
    return pojo;
  }
}
