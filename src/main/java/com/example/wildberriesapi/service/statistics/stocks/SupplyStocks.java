package com.example.wildberriesapi.service.statistics.stocks;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SupplyStocks {
    @JsonProperty("lastChangeDate")
    private LocalDateTime lastChangeDate;

    @JsonProperty("warehouseName")
    @Max(50)
    private String warehouseName;

    @JsonProperty("supplierArticle")
    @Max(75)
    private String supplierArticle;

    @JsonProperty("nmId")
    private int nmId;

    @JsonProperty("barcode")
    @Max(30)
    private String barcode;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("inWayToClient")
    private int inWayToClient;

    @JsonProperty("inWayFromClient")
    private int inWayFromClient;

    @JsonProperty("quantityFull")
    private int quantityFull;

    @JsonProperty("category")
    @Max(50)
    private String category;

    @JsonProperty("subject")
    @Max(50)
    private String subject;

    @JsonProperty("brand")
    @Max(50)
    private String brand;

    @JsonProperty("techSize")
    @Max(30)
    private String techSize;

    @JsonProperty("Price")
    private double price;

    @JsonProperty("Discount")
    private double discount;

    @JsonProperty("isSupply")
    private boolean isSupply;

    @JsonProperty("isRealization")
    private boolean isRealization;

    @JsonProperty("SCCode")
    @Max(50)
    private String SCCode;
}
