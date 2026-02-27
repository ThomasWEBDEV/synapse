package com.synapse.controller;

import com.synapse.model.FluxStatus;
import com.synapse.repository.DataFluxRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final DataFluxRepository repository;

    public StatsController(DataFluxRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Map<String, Long>> getStats() {
        return ResponseEntity.ok(Map.of(
            "total",   repository.count(),
            "pending", repository.findByStatus(FluxStatus.PENDING).size(),
            "success", repository.findByStatus(FluxStatus.SUCCESS).size(),
            "failed",  repository.findByStatus(FluxStatus.FAILED).size(),
            "retry",   repository.findByStatus(FluxStatus.RETRY).size()
        ));
    }
}
