package com.example.wildberriesapi.service.statistics.sales;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum OrderType {
    CLIENT_ORDER("Клиентский"),
    RETURN_DEFECT("Возврат Брака"),
    FORCED_RETURN("Принудительный возврат"),
    ANONYMOUS_RETURN("Возврат обезлички"),
    WRONG_ATTACHMENT_RETURN("Возврат Неверного Вложения"),
    SELLER_RETURN("Возврат Продавца"),
    RECALL_RETURN("Возврат из Отзыва"),
    AUTO_RETURN_MP("АвтоВозврат МП"),
    INCOMPLETE_RETURN("Недокомплект (Вина продавца)"),
    KGT_RETURN("Возврат КГТ");

    private final String description;

    @JsonValue
    public String getDescription() {
        return this.description;
    }
}
