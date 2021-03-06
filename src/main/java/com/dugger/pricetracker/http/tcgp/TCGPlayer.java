package com.dugger.pricetracker.http.tcgp;

import com.dugger.pricetracker.http.Get;
import com.dugger.pricetracker.http.Post;
import com.dugger.pricetracker.http.Request;
import com.dugger.pricetracker.http.models.JsonPojo;
import com.dugger.pricetracker.http.tcgp.models.*;
import com.dugger.pricetracker.util.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TCGPlayer {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  public BearerToken bearerToken;


  @Value("${tcgp.api.public.key}")
  private String clientId;
  @Value("${tcgp.api.private.key}")
  private String clientSecret;

  @Value("${tcgp.api.domain}")
  private String domain;
  @Value("${tcgp.api.version}")
  private String version;

  private final Map<String, String> headers;

  private final ObjectMapper mapper = new ObjectMapper();

  public TCGPlayer() {
    bearerToken = new BearerToken();
    headers = new HashMap<>();
    headers.put("Accept", "application/json");
  }

  public String getUrlBase() { return domain + "/" + version; }

  /* returns true if success or nothing was needed. false if there was a problem */
  public boolean authenticate() {
    if (bearerToken.shouldRenew()) {
      Post post = getBasePost();
      // this is the only TCGP endpoint that doesn't include the version in the path
      post.setUrlBase(domain);
      post.setEndpoint("/token");
      Map<String, String> params = new HashMap<>();
      params.put("grant_type", "client_credentials");
      params.put("client_id", clientId);
      params.put("client_secret", clientSecret);
      post.setParams(params);
      return Optional.ofNullable(parseResponse(post.sendRequest(), Authenticate.class))
        .map(auth -> {
          if (StringUtils.hasText(auth.getError())) {
            logger.error(String.format(
              "There was an error authenticating with TCGPlayer: %s. Details: %s: ",
              auth.getError(),
              auth.getErrorMessage()
            ));
            return null;
          }
          bearerToken.setToken(auth.getAccessToken());
          bearerToken.setExpiryTime(auth.getExpires().getTime());
          headers.put("Authorization", "Bearer " + bearerToken.getToken());
          return auth;
        })
        .isPresent();
    }
    return true;
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

  public Products getGroupProducts(int groupId, int limit, int offset) {
    Get get = getBaseGet();
    Map<String, String> params = new HashMap<>();
    params.put("limit", Integer.toString(limit));
    params.put("offset", Integer.toString(offset));
    params.put("groupId", Integer.toString(groupId));
    params.put("getExtendedFields", "true");
    params.put("includeSkus", "true");
    get.setParams(params);
    get.setEndpoint("/catalog/products");
    return parseResponse(get.sendRequest(), Products.class);
  }

  public Rarities getRarities(int categoryId) {
    Get get = getBaseGet();
    get.setParams(Request.emptyParams);
    get.setEndpoint("/catalog/categories/" + categoryId + "/rarities");
    return parseResponse(get.sendRequest(), Rarities.class);
  }

  public Printings getPrintings(int categoryId) {
    Get get = getBaseGet();
    get.setParams(Request.emptyParams);
    get.setEndpoint("/catalog/categories/" + categoryId + "/printings");
    return parseResponse(get.sendRequest(), Printings.class);
  }

  public Conditions getConditions(int categoryId) {
    Get get = getBaseGet();
    get.setParams(Request.emptyParams);
    get.setEndpoint("/catalog/categories/" + categoryId + "/conditions");
    return parseResponse(get.sendRequest(), Conditions.class);
  }

  public Languages getLanguages(int categoryId) {
    Get get = getBaseGet();
    get.setParams(Request.emptyParams);
    get.setEndpoint("/catalog/categories/" + categoryId + "/languages");
    return parseResponse(get.sendRequest(), Languages.class);
  }

  public Get getBaseGet() { return new Get(null, headers, getUrlBase(), null, true); }

  public Post getBasePost() { return  new Post(null, headers, getUrlBase(), null, true); }

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
