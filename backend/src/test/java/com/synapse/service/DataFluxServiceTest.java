package com.synapse.service;

import com.synapse.model.DataFlux;
import com.synapse.repository.DataFluxRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataFluxServiceTest {

    @Mock
    private DataFluxRepository repository;

    @InjectMocks
    private DataFluxService service;

    private DataFlux flux;

    @BeforeEach
    void setUp() {
        flux = new DataFlux();
        flux.setId(1L);
        flux.setSource("ERP");
        flux.setDestination("WMS");
        flux.setPayload("{\"order_id\": \"CMD-001\"}");
    }

    @Test
    void create_doitDefinirStatutPending() {
        // ARRANGE
        when(repository.save(any(DataFlux.class))).thenReturn(flux);

        // ACT
        DataFlux resultat = service.create(flux);

        // ASSERT
        assertEquals("PENDING", flux.getStatus());
        verify(repository, times(1)).save(flux);
    }

    @Test
    void getById_doitRetournerFlux() {
        // ARRANGE
        when(repository.findById(1L)).thenReturn(Optional.of(flux));

        // ACT
        DataFlux resultat = service.getById(1L);

        // ASSERT
        assertEquals("ERP", resultat.getSource());
        assertEquals("WMS", resultat.getDestination());
    }

    @Test
    void getById_fluxIntrouvable_doitLeverException() {
        // ARRANGE
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // ACT + ASSERT
        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.getById(99L));
        assertEquals("Flux introuvable : 99", ex.getMessage());
    }

    @Test
    void updateStatus_doitMettreAJourLeStatut() {
        // ARRANGE
        when(repository.findById(1L)).thenReturn(Optional.of(flux));
        when(repository.save(any(DataFlux.class))).thenReturn(flux);

        // ACT
        DataFlux resultat = service.updateStatus(1L, "SUCCESS");

        // ASSERT
        assertEquals("SUCCESS", flux.getStatus());
        verify(repository, times(1)).save(flux);
    }
}
