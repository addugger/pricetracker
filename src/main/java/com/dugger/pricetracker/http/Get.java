package com.dugger.pricetracker.http;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Map;

public class Get extends Request {

  public Get(Map<String, String> params, Map<String, String> headers,String urlBase, String endpoint, boolean secure) {
    super(params, headers, urlBase, endpoint, secure);
  }

  @Override
  protected String getUrl() { return getUrlPrefix() + urlBase + endpoint + "?" + getParamString(); }

  @Override
  protected HttpRequest createRequest() {
    HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(URI.create(getUrl())).GET();
    headers.forEach(requestBuilder::setHeader);
    return requestBuilder.build();
  }
}
