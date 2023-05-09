package com.pruebas.liti.Repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.pruebas.liti.entity.UserEntityProof;

public interface IUserProofRepository extends R2dbcRepository<UserEntityProof, Long>{
    
}
