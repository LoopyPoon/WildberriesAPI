package com.example.wildberriesapi.service.statistics.sales;

import com.example.wildberriesapi.service.statistics.orders.OrderType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SupplySales {
    @JsonProperty("date")
    private LocalDateTime date;

    @JsonProperty("lastChangeDate")
    private LocalDateTime lastChangeDate;

    @JsonProperty("warehouseName")
    @Max(50)
    private String warehouseName;

    @JsonProperty("countryName")
    @Max(200)
    private String countryName;

    @JsonProperty("oblastOkrugName")
    @Max(200)
    private String oblastOkrugName;

    @JsonProperty("regionName")
    @Max(200)
    private String regionName;

    @JsonProperty("supplierArticle")
    @Max(75)
    private String supplierArticle;

    @JsonProperty("nmId")
    private int nmId;

    @JsonProperty("barcode")
    @Max(30)
    private String barcode;

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

    @JsonProperty("incomeID")
    private int incomeID;

    @JsonProperty("isSupply")
    private boolean isSupply;

    @JsonProperty("isRealization")
    private boolean isRealization;

    @JsonProperty("totalPrice")
    private double totalPrice;

    @JsonProperty("discountPercent")
    private double discountPercent;

    @JsonProperty("spp")
    private double spp;

    @JsonProperty("paymentSaleAmount")
    private int paymentSaleAmount;

    @JsonProperty("forPay")
    private double forPay;

    @JsonProperty("finishedPrice")
    private double finishedPrice;

    @JsonProperty("priceWithDisc")
    private double priceWithDisc;

    @JsonProperty("saleID")
    @Max(15)
    private String saleID;

    @JsonProperty("orderType")
    private OrderType orderType;

    @JsonProperty("sticker")
    private String sticker;

    @JsonProperty("gNumber")
    @Max(50)
    private String gNumber;

    @JsonProperty("srid")
    private String srid;
}
