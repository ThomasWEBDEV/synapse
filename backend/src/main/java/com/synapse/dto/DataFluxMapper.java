package com.synapse.dto;

import com.synapse.model.DataFlux;
import org.springframework.stereotype.Component;

@Component
public class DataFluxMapper {

    public DataFlux toEntity(DataFluxDTO dto) {
        DataFlux flux = new DataFlux();
        flux.setSource(dto.getSource());
        flux.setDestination(dto.getDestination());
        flux.setPayload(dto.getPayload());
        return flux;
    }

    public DataFluxDTO toDTO(DataFlux flux) {
        DataFluxDTO dto = new DataFluxDTO();
        dto.setSource(flux.getSource());
        dto.setDestination(flux.getDestination());
        dto.setPayload(flux.getPayload());
        return dto;
    }
}
