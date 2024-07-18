package com.example.wildberriesapi.analytics;

import lombok.Data;

import java.util.List;

@Data
public class AnalyticResponse {
    private List<Analytic> data;
    private boolean error;
    private String errorText;
    private List<AdditionalError> additionalErrors;
}
