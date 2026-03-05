package com.synapse.wms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/wms")
public class WmsController {

    @PostMapping("/receive")
    public ResponseEntity<Map<String, String>> receiveFlux(@RequestBody Map<String, Object> flux) {
        System.out.println("Flux recu depuis Synapse : " + flux);
        return ResponseEntity.ok(Map.of(
            "status", "RECEIVED",
            "message", "Flux traite par le WMS"
        ));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "service", "Synapse WMS"
        ));
    }
}
