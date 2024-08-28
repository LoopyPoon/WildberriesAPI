package com.example.wildberriesapi.service.analytics.nm_report;

import lombok.Data;

import java.util.List;

@Data
public class AnalyticResponse {
    private List<Analytic> data;
    private boolean error;
    private String errorText;
    private List<AdditionalError> additionalErrors;
}
