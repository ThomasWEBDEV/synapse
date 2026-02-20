package com.synapse.service;

import com.synapse.model.DataFlux;
import com.synapse.repository.DataFluxRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DataFluxService {

    private final DataFluxRepository repository;

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
        return repository.save(flux);
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
