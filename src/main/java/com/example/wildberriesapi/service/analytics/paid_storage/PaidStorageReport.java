package com.example.wildberriesapi.service.analytics.paid_storage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PaidStorageReport {
    // Дата, за которую был расчёт или перерасчёт
    @JsonProperty("date")
    private String date;

    // Коэффициент логистики и хранения
    @JsonProperty("logWarehouseCoef")
    private double logWarehouseCoef;

    // ID склада
    @JsonProperty("officeId")
    private long officeId;

    // Название склада
    @JsonProperty("warehouse")
    private String warehouse;

    // Коэффициент склада
    @JsonProperty("warehouseCoef")
    private double warehouseCoef;

    // ID поставки
    @JsonProperty("giId")
    private long giId;

    // Идентификатор размера для этого артикула Wildberries
    @JsonProperty("chrtId")
    private long chrtId;

    // Размер (techSize в карточке товара)
    @JsonProperty("size")
    private String size;

    // Баркод
    @JsonProperty("barcode")
    private String barcode;

    // Предмет
    @JsonProperty("subject")
    private String subject;

    // Бренд
    @JsonProperty("brand")
    private String brand;

    // Артикул продавца
    @JsonProperty("vendorCode")
    private String vendorCode;

    // Артикул Wildberries
    @JsonProperty("nmId")
    private long nmId;

    // Объём товара
    @JsonProperty("volume")
    private double volume;

    // Способ расчёта
    @JsonProperty("calcType")
    private String calcType;

    // Сумма хранения
    @JsonProperty("warehousePrice")
    private double warehousePrice;

    // Количество единиц товара (штук), подлежащих тарифицированию за расчётные сутки
    @JsonProperty("barcodesCount")
    private long barcodesCount;

    // Код паллетоместа
    @JsonProperty("palletPlaceCode")
    private long palletPlaceCode;

    // Количество паллет
    @JsonProperty("palletCount")
    private long palletCount;

    // Если был перерасчёт, это дата первоначального расчёта
    @JsonProperty("originalDate")
    private String originalDate;

    // Скидка программы лояльности, ₽
    @JsonProperty("loyaltyDiscount")
    private double loyaltyDiscount;

    // Дата фиксации тарифа
    @JsonProperty("tariffFixDate")
    private String tariffFixDate;

    // Дата понижения тарифа
    @JsonProperty("tariffLowerDate")
    private String tariffLowerDate;
}
