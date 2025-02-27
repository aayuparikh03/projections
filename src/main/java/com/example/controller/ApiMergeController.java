package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiMergeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiMergeController.class);
    private final WebClient webClient;

    public ApiMergeController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @GetMapping("/merged-api-docs")
    public ResponseEntity<Map<String, Object>> getMergedApiDocs() {
        try {
            Mono<Map> service1Mono = webClient.get()
                    .uri("http://localhost:8082/v3/api-docs")
                    .retrieve()
                    .bodyToMono(Map.class)
                    .defaultIfEmpty(Collections.emptyMap());

            Mono<Map> service2Mono = webClient.get()
                    .uri("http://localhost:8080/v3/api-docs")
                    .retrieve()
                    .bodyToMono(Map.class)
                    .defaultIfEmpty(Collections.emptyMap());

            Map<String, Object> mergedSpec = Mono.zip(service1Mono, service2Mono)
                    .map(tuple -> mergeSpecs(tuple.getT1(), tuple.getT2()))
                    .block();

            return ResponseEntity.ok(mergedSpec);
        } catch (Exception e) {
            LOGGER.error("Error fetching API specs", e);
            return ResponseEntity.internalServerError().body(Map.of("error", "Error fetching API specs"));
        }
    }

    private Map<String, Object> mergeSpecs(Map<String, Object> service1, Map<String, Object> service2) {
        Map<String, Object> mergedPaths = new HashMap<>();
        mergedPaths.putAll((Map<String, Object>) service1.getOrDefault("paths", Collections.emptyMap()));
        mergedPaths.putAll((Map<String, Object>) service2.getOrDefault("paths", Collections.emptyMap()));

        Map<String, Object> mergedSchemas = new HashMap<>();
        Map<String, Object> schemas1 = (Map<String, Object>) ((Map<String, Object>) service1.getOrDefault("components", Collections.emptyMap())).getOrDefault("schemas", Collections.emptyMap());
        Map<String, Object> schemas2 = (Map<String, Object>) ((Map<String, Object>) service2.getOrDefault("components", Collections.emptyMap())).getOrDefault("schemas", Collections.emptyMap());
        mergedSchemas.putAll(schemas1);
        mergedSchemas.putAll(schemas2);

        return Map.of(
                "openapi", "3.0.0",
                "info", Map.of("title", "Combined API", "version", "1.0.0"),
                "paths", mergedPaths,
                "components", Map.of("schemas", mergedSchemas)
        );
    }
}
