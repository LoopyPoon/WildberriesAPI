package com.example.wildberriesapi.service.statistics.incomes;

import jakarta.validation.constraints.Max;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SupplyIncomes {
    private int incomeId;
    @Max(40)
    private String number;
    private LocalDateTime date;
    private LocalDateTime lastChangeDate;
    @Max(75)
    private String supplierArticle;
    @Max(30)
    private String techSize;
    @Max(30)
    private String barcode;
    private int quantity;
    private double totalPrice;
    private LocalDateTime dateClose;
    @Max(50)
    private String warehouseName;
    private int nmId;
    @Max(50)
    private String status;
}
