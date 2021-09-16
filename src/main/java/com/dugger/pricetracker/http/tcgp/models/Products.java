package com.dugger.pricetracker.http.tcgp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Products extends TcgGetJsonPojo<Products.Product> {
    @Getter
    public static class Product implements TcgResult {
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

        Map<String, String> extendedDataMap = null;

        public Optional<String> getExtendedValue(String name) {
            if (extendedDataMap == null && extendedData!= null && !extendedData.isEmpty()) {
                extendedDataMap = extendedData.stream().collect(Collectors.toMap(ExtendedData::getName, ExtendedData::getName));
            }
            if (extendedDataMap != null) return Optional.ofNullable(extendedDataMap.get(name));
            else return Optional.empty();
        }
    }

    @Getter
    public static class Sku {
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

    @Getter
    public static class PresaleInfo {
        @JsonProperty
        boolean isPresale;
        @JsonProperty
        Timestamp releasedOn;
        @JsonProperty
        String note;
    }

    @Getter
    public static class ExtendedData {
        @JsonProperty
        String name;
        @JsonProperty
        String displayName;
        @JsonProperty
        String value;
    }
}
