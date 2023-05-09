package com.pruebas.liti.Repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.pruebas.liti.entity.UserEntityProof;

import reactor.core.publisher.Mono;

public interface IUserProofRepository extends R2dbcRepository<UserEntityProof, Long>{
    @Query("SELECT * FROM usuario WHERE email =:email")
    public Mono<UserEntityProof> findByEmail(String email);
}
