package com.dugger.pricetracker.http;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
abstract public class Request {
  protected static final Logger logger = LoggerFactory.getLogger(Get.class);
  protected static final HttpClient client = HttpClient.newHttpClient();

  protected Map<String, String> params;
  protected Map<String, String> headers;
  protected String urlBase;
  protected String endpoint;
  protected boolean secure;

  public Request(Map<String, String> params, Map<String, String> headers,String urlBase, String endpoint, boolean secure) {
    this.params = params;
    this.urlBase = urlBase;
    this.endpoint = endpoint;
    this.secure = secure;
    this.headers = headers;
  }

  protected abstract String getUrl();

  protected abstract HttpRequest createRequest();

  public String sendRequest() {
    String responseBody = null;
    HttpRequest request = createRequest();
    try {
      responseBody = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    } catch (Exception e) {
      logger.error("Problem sending request: " + request.toString(), e);
    }
    return  responseBody;
  }

  public static String encode(String unencodedString) {
    return URLEncoder.encode(unencodedString, StandardCharsets.UTF_8);
  }

  protected String getUrlPrefix() { return secure ? "https://" : "http://"; }

  protected String getParamString() {
    return params.entrySet().stream()
        .map(entry -> encode(entry.getKey()) + "=" + encode(entry.getValue()))
        .collect(Collectors.joining("&"));
  }
}
