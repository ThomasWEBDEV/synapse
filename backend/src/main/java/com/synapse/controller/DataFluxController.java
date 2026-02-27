package com.synapse.controller;

import com.synapse.model.DataFlux;
import com.synapse.service.DataFluxService;
import com.synapse.service.FluxValidatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/flux")
public class DataFluxController {

    private final DataFluxService service;
    private final FluxValidatorService validator;

    public DataFluxController(DataFluxService service, FluxValidatorService validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping
    public ResponseEntity<List<DataFlux>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataFlux> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<DataFlux> create(@RequestBody DataFlux flux) {
        validator.validate(flux);
        return ResponseEntity.ok(service.create(flux));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<DataFlux> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<DataFlux>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(service.getByStatus(status));
    }
}
