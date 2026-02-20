package com.synapse.repository;

import com.synapse.model.DataFlux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DataFluxRepository extends JpaRepository<DataFlux, Long> {
    List<DataFlux> findByStatus(String status);
    List<DataFlux> findBySource(String source);
}
