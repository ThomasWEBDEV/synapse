package com.synapse.service;

import com.synapse.model.DataFlux;
import org.springframework.stereotype.Service;

@Service
public class FluxValidatorService {

    public void validate(DataFlux flux) {
        if (flux.getSource() == null || flux.getSource().isBlank()) {
            throw new RuntimeException("Le champ 'source' est obligatoire");
        }
        if (flux.getDestination() == null || flux.getDestination().isBlank()) {
            throw new RuntimeException("Le champ 'destination' est obligatoire");
        }
        if (flux.getPayload() == null || flux.getPayload().isBlank()) {
            throw new RuntimeException("Le champ 'payload' est obligatoire");
        }
    }
}
