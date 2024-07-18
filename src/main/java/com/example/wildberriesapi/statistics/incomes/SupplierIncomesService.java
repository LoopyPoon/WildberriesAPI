package com.example.wildberriesapi.statistics.incomes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class SupplierIncomesService {
    private static final String API_KEY = "eyJhbGciOiJFUzI1NiIsImtpZCI6IjIwMjQwNzE1djEiLCJ0eXAiOiJKV1QifQ.eyJlbnQiOjEsImV4cCI6MTczNjk4NjU0MywiaWQiOiI4MWEzYmJkMi1iYmFmLTQ2MTYtODRhMi0yNDM5OTRlMWNhY2EiLCJpaWQiOjMwMTI3MjgwLCJvaWQiOjQ4MjQ4LCJzIjoxMDczNzQxODYwLCJzaWQiOiJkZWNkZGU5Ny04Y2NmLTVkZDQtYTI0MS00YmFkYzA2N2IzZjYiLCJ0IjpmYWxzZSwidWlkIjozMDEyNzI4MH0.zYEyocKmGKNzIPeLzVCnDHgB-UD2MwUU2IA3166_b5keUN3Kw_cmSTcjJBFGm22nGNY5Cd7flTY7N4le5lI5AA";
    private static final String API_URL = "https://statistics-api.wildberries.ru/api/v1/supplier/incomes";
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public SupplierIncomesService() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public List<SupplyIncomes> getSuppliers() {
        LocalDate dateFrom = LocalDate.of(2023, 1, 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = dateFrom.format(formatter);

        String params = "?dateFrom=" + formattedDate;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + params))
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            return objectMapper.readValue(responseBody, new TypeReference<>() {});
        } catch (Exception e) {
            log.error("Error", e);
            return Collections.emptyList();
        }
    }
}
