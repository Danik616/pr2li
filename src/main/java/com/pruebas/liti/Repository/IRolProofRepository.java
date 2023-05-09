package com.pruebas.liti.Repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.pruebas.liti.entity.RolEntityProof;

import reactor.core.publisher.Mono;

public interface IRolProofRepository extends R2dbcRepository<RolEntityProof, Long>{
    @Query("SELECT * FROM roles WHERE id= :id")
    public Mono<RolEntityProof> findById(long id);
}
