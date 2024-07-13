package com.example.wildberriesapi.statistics;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class SupplierService {
    private static final String API_KEY = "test-token";
    private static final String API_URL = "https://statistics-api.wildberries.ru/api/v1/supplier/incomes";
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public SupplierService() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

//    public List<Supply> getSuppliers() {
//        LocalDate dateFrom = LocalDate.of(2024, 1, 1);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String formattedDate = dateFrom.format(formatter);
//
//        String params = "?dateFrom=" + formattedDate;
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(API_URL + params))
//                .header("Authorization", "Bearer " + API_KEY)
//                .header("Content-Type", "application/json")
//                .GET()
//                .build();
//
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            String responseBody = response.body();
//            return objectMapper.readValue(responseBody, new TypeReference<List<Supply>>() {});
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public List<Supply> getSuppliers() {
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
            System.out.println(responseBody); // Вывод JSON-ответа в консоль
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            // Проверьте, является ли корневой элемент массива или объекта
            if (jsonNode.isArray()) {
                return objectMapper.readValue(responseBody, new TypeReference<List<Supply>>() {});
            } else {
                System.out.println("Unexpected JSON structure");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
