package com.example.wildberriesapi.service.analytics.paid_storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class PaidStorageReportService {
    private static final String API_KEY = "eyJhbGciOiJFUzI1NiIsImtpZCI6IjIwMjQwODAxdjEiLCJ0eXAiOiJKV1QifQ.eyJlbnQiOjEsImV4cCI6MTczOTIyODgyOCwiaWQiOiJhOTZiNTRhNC01NmExLTQ2MTMtODNhMC1mYTVlZGMyYTM4MDAiLCJpaWQiOjMwMTI3MjgwLCJvaWQiOjQ4MjQ4LCJzIjoxMDczNzQxOTI0LCJzaWQiOiJkZWNkZGU5Ny04Y2NmLTVkZDQtYTI0MS00YmFkYzA2N2IzZjYiLCJ0IjpmYWxzZSwidWlkIjozMDEyNzI4MH0.lN8r9gtzSVhCVmUOKf4dZAZon6yaDkQEHDxlwtovq7XjL7DLQOGW_VA2H2J44wya2-cW4lvC8WigLSI5mwZ1Pg";
    private static final String CREATE_REPORT_URL = "https://seller-analytics-api.wildberries.ru/api/v1/paid_storage";
    private static final String CHECK_REPORT_URL = "https://seller-analytics-api.wildberries.ru/api/v1/paid_storage/tasks/%s/status";
    private static final String GET_REPORT_URL = "https://seller-analytics-api.wildberries.ru/api/v1/paid_storage/tasks/%s/download";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public PaidStorageReportService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public String createPaidStorageReport(LocalDate dateFrom, LocalDate dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateFrom = dateFrom.format(formatter);
        String formattedDateTo = dateTo.format(formatter);

        String params = String.format("?dateFrom=%s&dateTo=%s", formattedDateFrom, formattedDateTo);
        String fullUrl = CREATE_REPORT_URL + params;

        log.info("Request URL: {}", fullUrl);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(CREATE_REPORT_URL + params))
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            log.info("Response from Wildberries API: {}", responseBody);

            String taskId = extractTaskId(responseBody);

            if (taskId == null || taskId.isEmpty()) {
                log.error("Failed to extract taskId from the response");
                return null;
            }

            log.info("Extracted taskId: {}", taskId);
            return taskId;

//            return extractTaskId(responseBody);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Метод для проверки статуса отчета
    public String checkPaidStorageReportStatus(String taskId) {
        String url = String.format(CHECK_REPORT_URL, taskId);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            log.info("Check report status response: {}", responseBody);

            return extractReportStatus(responseBody);
        } catch (Exception e) {
            log.error("Error checking report status", e);
            return null;
        }
    }

    // Метод для получения отчета
    public List<PaidStorageReport> downloadPaidStorageReport(String taskId) {
        String url = String.format(GET_REPORT_URL, taskId);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            log.info("Download report response: {}", responseBody);

            return objectMapper.readValue(responseBody, new TypeReference<>() {});  // Вернуть отчет в виде строки или обработать его дальше
        } catch (Exception e) {
            log.error("Error downloading report", e);
            return null;
        }
    }

    // Вспомогательный метод для извлечения taskId из ответа на создание отчета
    private String extractTaskId(String responseBody) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            return rootNode.path("data").path("taskId").asText();
        } catch (Exception e) {
            log.error("Error extracting taskId", e);
            return null;
        }
    }

    // Вспомогательный метод для извлечения статуса отчета
    private String extractReportStatus(String responseBody) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            return rootNode.path("data").path("status").asText();
        } catch (Exception e) {
            log.error("Error extracting report status", e);
            return null;
        }
    }
}
