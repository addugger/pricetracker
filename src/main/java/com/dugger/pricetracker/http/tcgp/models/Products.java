package com.dugger.pricetracker.http.tcgp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;

public class Products extends TcgGetJsonPojo<Products.Product> {
    protected static class Product implements TcgResult {
        @JsonProperty
        int productId;
        @JsonProperty
        String name;
        @JsonProperty
        String cleanName;
        @JsonProperty
        String imageUrl;
        @JsonProperty
        int categoryId;
        @JsonProperty
        int groupId;
        @JsonProperty
        String url;
        @JsonProperty
        Timestamp modifiedOn;
        @JsonProperty
        List<Sku> skus;
        @JsonProperty
        int imageCount;
        @JsonProperty
        PresaleInfo presaleInfo;
        @JsonProperty
        List<ExtendedData> extendedData;
    }

    protected static class Sku {
        @JsonProperty
        int skuId;
        @JsonProperty
        int productId;
        @JsonProperty
        int languageId;
        @JsonProperty
        int printingId;
        @JsonProperty
        int conditionId;
    }

    protected static class PresaleInfo {
        @JsonProperty
        boolean isPresale;
        @JsonProperty
        Timestamp releasedOn;
        @JsonProperty
        String note;
    }

    protected static class ExtendedData {
        @JsonProperty
        String name;
        @JsonProperty
        String displayName;
        @JsonProperty
        String value;
    }
}
