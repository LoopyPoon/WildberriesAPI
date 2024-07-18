package com.example.wildberriesapi.analytics;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class AnalyticService {
    private static final String API_KEY = "ZXlKaGJHY2lPaUpGVXpJMU5pSXNJbXRwWkNJNklqSXdNalF3TlRBMmRqRWlMQ0owZVhBaU9pSktWMVFpZlEuZXlKbGJuUWlPakVzSW1WNGNDSTZNVGN6TXpVNU9EUTROeXdpYVdRaU9pSXhPVEUzTjJSaVlpMDBOVEkwTFRRd01HSXRPV0V3WVMwMU1qTXlZamxoWldRek5qY2lMQ0pwYVdRaU9qazNNamM0TVRreExDSnZhV1FpT2pFeU1UYzNNamdzSW5NaU9qRXdOek0zTkRFNU16SXNJbk5wWkNJNkltUmpZamN5WmpRMkxUTmxNVGt0TkRkaFppMWlZVGRtTFRsa01UaGlOVGxrTmpGa1lpSXNJblFpT21aaGJITmxMQ0oxYVdRaU9qazNNamM0TVRreGZRLjVicEFlbGx0RjlFZjR6dnI0TGtJUVBiV1Jxek9rbm9qaDBRYmpfaXFFeExvU0JFWG5XUjRXdDJfS1gxaGxMOHZuYUc0YUwzOUNnSGp6bmFEZG1KbWhR";
    private static final String API_URL = "https://statistics-api.wildberries.ru/api/v1/supplier/incomes";
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public AnalyticService() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public AnalyticResponse getReports(List<String> downloadsIds) {

        StringBuilder params = new StringBuilder("?filter[downloadIds]=");

        for (String id : downloadsIds) {
            params.append(id).append(";\n");
        }

        // Удаляем последнюю запятую
        if (!params.isEmpty()) {
            params.setLength(params.length() - 1);
        }

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
            if (jsonNode.has("data") && jsonNode.get("data").isArray()) {
                return objectMapper.readValue(jsonNode.get("data").toString(), new TypeReference<>() {
                });
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
