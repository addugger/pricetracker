package com.dugger.pricetracker.http;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Map;

public class Post extends Request {

  public Post(Map<String, String> params, Map<String, String> headers, String urlBase, String endpoint, boolean secure) {
    super(params, headers, urlBase, endpoint, secure);
  }

  @Override
  protected String getUrl() { return getUrlPrefix() + urlBase + endpoint; }

  @Override
  protected HttpRequest createRequest() {
    HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
        .uri(URI.create(getUrl()))
        .POST(HttpRequest.BodyPublishers.ofString(getParamString()));
    headers.forEach(requestBuilder::setHeader);
    return requestBuilder.build();
  }
}
