package com.example.wildberriesapi.service.advance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CostHistoryData {
    // Номер выставленного документа (при наличии)
    @JsonProperty("updNum")
    private long updNum;

    // Время списания
    @JsonProperty("updTime")
    private OffsetDateTime updTime;

    // Выставленная сумма
    @JsonProperty("updSum")
    private long updSum;

    // Идентификатор кампании
    @JsonProperty("advertId")
    private long advertId;

    // Название кампании
    @JsonProperty("campName")
    private String campName;

    // Тип кампании
    @JsonProperty("advertType")
    private long advertType;

    // Источник списания
    @JsonProperty("paymentType")
    private String paymentType;

    // Статус кампании
    @JsonProperty("advertStatus")
    private int advertStatus;

}
