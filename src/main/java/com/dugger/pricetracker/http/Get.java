package com.dugger.pricetracker.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Get {

    private static final Logger logger = LoggerFactory.getLogger(Get.class);

    private static final HttpClient client = HttpClient.newHttpClient();

    private final Map<String, String> params;
    private Map<String, String> headers;
    private final String domain;
    private final String endpoint;
    private final boolean secure;

    private final HttpRequest request;

    public Get(Map<String, String> params, Map<String, String> headers,String domain, String endpoint, boolean secure) {
        this.params = params;
        this.domain = domain;
        this.endpoint = endpoint;
        this.secure = secure;
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(URI.create(getUrl())).GET();
        headers.forEach(requestBuilder::setHeader);
        this.request = requestBuilder.build();
    }

    public String sendRequest() {
        String responseBody = null;
        try {
            responseBody = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            logger.error("Problem sending request: " + request.toString(), e);
        }
        return  responseBody;
    }

    private String getUrlPrefix() { return secure ? "https//" : "http//"; }

    private String getParamString() {
        StringBuilder paramString = new StringBuilder("?");
        params.entrySet().stream().forEach(entry ->
                paramString.append(encode(entry.getKey())).append("=").append(encode(entry.getValue())).append("&")
        );
        return  paramString.toString();
    }

    private String getUrl() { return getUrlPrefix() + domain + endpoint + getParamString(); }

    private static String encode(String unencodedString) {
       return URLEncoder.encode(unencodedString, StandardCharsets.UTF_8);
    }
}
