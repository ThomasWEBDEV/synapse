package com.synapse.service;

import com.synapse.model.DataFlux;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FluxValidatorServiceTest {

    private FluxValidatorService validator;

    @BeforeEach
    void setUp() {
        validator = new FluxValidatorService();
    }

    @Test
    void fluxValide_nedoitPasLeverException() {
        // ARRANGE
        DataFlux flux = new DataFlux();
        flux.setSource("ERP");
        flux.setDestination("WMS");
        flux.setPayload("{\"order_id\": \"CMD-001\"}");

        // ACT + ASSERT
        assertDoesNotThrow(() -> validator.validate(flux));
    }

    @Test
    void sourceVide_doitLeverException() {
        // ARRANGE
        DataFlux flux = new DataFlux();
        flux.setSource("");
        flux.setDestination("WMS");
        flux.setPayload("{\"order_id\": \"CMD-001\"}");

        // ACT + ASSERT
        RuntimeException ex = assertThrows(RuntimeException.class, () -> validator.validate(flux));
        assertEquals("Le champ 'source' est obligatoire", ex.getMessage());
    }

    @Test
    void destinationVide_doitLeverException() {
        // ARRANGE
        DataFlux flux = new DataFlux();
        flux.setSource("ERP");
        flux.setDestination("");
        flux.setPayload("{\"order_id\": \"CMD-001\"}");

        // ACT + ASSERT
        RuntimeException ex = assertThrows(RuntimeException.class, () -> validator.validate(flux));
        assertEquals("Le champ 'destination' est obligatoire", ex.getMessage());
    }

    @Test
    void payloadVide_doitLeverException() {
        // ARRANGE
        DataFlux flux = new DataFlux();
        flux.setSource("ERP");
        flux.setDestination("WMS");
        flux.setPayload("");

        // ACT + ASSERT
        RuntimeException ex = assertThrows(RuntimeException.class, () -> validator.validate(flux));
        assertEquals("Le champ 'payload' est obligatoire", ex.getMessage());
    }
}
