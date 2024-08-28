package com.example.wildberriesapi.service.statistics.stocks;

import com.fasterxml.jackson.core.type.TypeReference;
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
public class SupplierStocksService {
    private static final String API_KEY = "eyJhbGciOiJFUzI1NiIsImtpZCI6IjIwMjQwODAxdjEiLCJ0eXAiOiJKV1QifQ.eyJlbnQiOjEsImV4cCI6MTczOTIyODgyOCwiaWQiOiJhOTZiNTRhNC01NmExLTQ2MTMtODNhMC1mYTVlZGMyYTM4MDAiLCJpaWQiOjMwMTI3MjgwLCJvaWQiOjQ4MjQ4LCJzIjoxMDczNzQxOTI0LCJzaWQiOiJkZWNkZGU5Ny04Y2NmLTVkZDQtYTI0MS00YmFkYzA2N2IzZjYiLCJ0IjpmYWxzZSwidWlkIjozMDEyNzI4MH0.lN8r9gtzSVhCVmUOKf4dZAZon6yaDkQEHDxlwtovq7XjL7DLQOGW_VA2H2J44wya2-cW4lvC8WigLSI5mwZ1Pg";
    private static final String API_URL = "https://statistics-api.wildberries.ru/api/v1/supplier/stocks";
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public SupplierStocksService() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public List<SupplyStocks> getStocks(LocalDate dateFrom) {
//        log.info("Using API_KEY: {}", API_KEY);

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


            log.info("API response body: {}", responseBody);

            return objectMapper.readValue(responseBody, new TypeReference<>() {});
        } catch (Exception e) {
            log.error("Error", e);
            return Collections.emptyList();
        }
    }
}