package com.example.wildberriesapi.analytics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum Status {
    WAITING("в очереди на обработку"),
    PROCESSING("генерируется"),
    SUCCESS("готов"),
    RETRY("ожидает повторной обработки"),
    FAILED("не получилось сгенерировать, сгенерируйте повторно");

    private final String title;
}
