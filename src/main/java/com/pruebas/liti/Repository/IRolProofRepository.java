package com.pruebas.liti.Repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;

import com.pruebas.liti.entity.RolEntityProof;

import reactor.core.publisher.Mono;

public interface IRolProofRepository extends R2dbcRepository<RolEntityProof, Long>{
    @Query("SELECT * FROM roles WHERE rol_id= :id")
    public Mono<RolEntityProof> findById(@Param("id") Long id);
}
