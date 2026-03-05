package com.synapse.service;

import com.synapse.model.DataFlux;
import com.synapse.repository.DataFluxRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataFluxService {

    private final DataFluxRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${wms.api.url:http://localhost:8082/api/wms/receive}")
    private String wmsApiUrl;

    public DataFluxService(DataFluxRepository repository) {
        this.repository = repository;
    }

    public List<DataFlux> getAll() {
        return repository.findAll();
    }

    public DataFlux getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flux introuvable : " + id));
    }

    public DataFlux create(DataFlux flux) {
        flux.setStatus("PENDING");
        DataFlux saved = repository.save(flux);

        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("id", saved.getId());
            payload.put("source", saved.getSource());
            payload.put("destination", saved.getDestination());
            payload.put("payload", saved.getPayload());

            restTemplate.postForObject(wmsApiUrl, payload, Map.class);
            saved.setStatus("SUCCESS");
            repository.save(saved);
            System.out.println("Flux " + saved.getId() + " transmis au WMS avec succes");
        } catch (Exception e) {
            saved.setStatus("FAILED");
            repository.save(saved);
            System.err.println("Erreur transmission WMS : " + e.getMessage());
        }

        return saved;
    }

    public DataFlux updateStatus(Long id, String status) {
        DataFlux flux = getById(id);
        flux.setStatus(status);
        return repository.save(flux);
    }

    public List<DataFlux> getByStatus(String status) {
        return repository.findByStatus(status);
    }
}
